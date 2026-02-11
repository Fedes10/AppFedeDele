package com.example.delefede.ui.dele

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import com.example.delefede.R
import com.example.delefede.data.remote.GitHubApiService
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import java.io.File

class LeerCancionFragment : Fragment() {
    private var player: ExoPlayer? = null
    private var audioUrl: String? = null
    private var audioLocal: String? = null
    private var isPlaying = false
    
    private lateinit var playPauseButton: FloatingActionButton
    private lateinit var progressSlider: Slider
    private lateinit var currentTimeText: TextView
    private lateinit var totalTimeText: TextView
    private lateinit var playerCard: MaterialCardView
    private lateinit var noAudioCard: MaterialCardView
    
    companion object {
        private const val TAG = "LeerCancionFragment"
    }
    
    private val handler = Handler(Looper.getMainLooper())
    private val updateProgressRunnable = object : Runnable {
        override fun run() {
            player?.let { exoPlayer ->
                if (exoPlayer.isPlaying) {
                    val currentPosition = exoPlayer.currentPosition
                    val duration = exoPlayer.duration
                    
                    if (duration > 0) {
                        progressSlider.value = (currentPosition * 100f / duration).coerceIn(0f, 100f)
                        currentTimeText.text = formatTime(currentPosition)
                        totalTimeText.text = formatTime(duration)
                    }
                }
            }
            handler.postDelayed(this, 500)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leer_cancion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val titulo = arguments?.getString("titulo") ?: ""
        val letra = arguments?.getString("letra") ?: ""
        val categoria = arguments?.getString("categoria") ?: ""
        audioUrl = arguments?.getString("audioUrl")
        audioLocal = arguments?.getString("audioLocal")

        setupToolbar(view)
        setupSongInfo(view, titulo, letra, categoria)
        setupPlayer(view)
        startEntryAnimations(view)
    }

    private fun setupToolbar(view: View) {
        view.findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupSongInfo(view: View, titulo: String, letra: String, categoria: String) {
        view.findViewById<TextView>(R.id.songTitle).text = titulo
        view.findViewById<TextView>(R.id.songLyrics).text = letra
        view.findViewById<TextView>(R.id.songCategory).text = categoria
    }

    private fun setupPlayer(view: View) {
        playPauseButton = view.findViewById(R.id.playPauseButton)
        progressSlider = view.findViewById(R.id.progressSlider)
        currentTimeText = view.findViewById(R.id.currentTime)
        totalTimeText = view.findViewById(R.id.totalTime)
        playerCard = view.findViewById(R.id.playerCard)
        noAudioCard = view.findViewById(R.id.noAudioCard)
        
        val rewindButton = view.findViewById<View>(R.id.rewindButton)
        val forwardButton = view.findViewById<View>(R.id.forwardButton)

        Log.d(TAG, "audioUrl: $audioUrl")
        Log.d(TAG, "audioLocal: $audioLocal")

        // Determinar la URI del audio: primero local, luego remota
        val mediaUri: String? = when {
            !audioLocal.isNullOrEmpty() && File(audioLocal!!).exists() -> {
                Log.d(TAG, "Usando audio local: $audioLocal")
                audioLocal
            }
            !audioUrl.isNullOrEmpty() -> {
                val fullUrl = GitHubApiService.BASE_URL + audioUrl
                Log.d(TAG, "Usando audio remoto: $fullUrl")
                fullUrl
            }
            else -> {
                Log.d(TAG, "No hay audio disponible")
                null
            }
        }

        if (mediaUri.isNullOrEmpty()) {
            playerCard.visibility = View.GONE
            noAudioCard.visibility = View.VISIBLE
            return
        }

        playerCard.visibility = View.VISIBLE
        noAudioCard.visibility = View.GONE

        // Inicializar ExoPlayer
        player = ExoPlayer.Builder(requireContext()).build().apply {
            try {
                setMediaItem(MediaItem.fromUri(mediaUri))
                prepare()
                
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        Log.d(TAG, "Estado del player: $state")
                        when (state) {
                            Player.STATE_ENDED -> {
                                this@LeerCancionFragment.isPlaying = false
                                updatePlayPauseButton()
                                progressSlider.value = 0f
                                currentTimeText.text = "0:00"
                                seekTo(0)
                            }
                            Player.STATE_READY -> {
                                Log.d(TAG, "Audio listo para reproducir")
                                totalTimeText.text = formatTime(duration)
                            }
                            Player.STATE_BUFFERING -> {
                                Log.d(TAG, "Buffering...")
                            }
                        }
                    }
                    
                    override fun onPlayerError(error: PlaybackException) {
                        Log.e(TAG, "Error en reproducción: ${error.message}")
                        Toast.makeText(
                            requireContext(),
                            "Error al reproducir: ${error.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        playerCard.visibility = View.GONE
                        noAudioCard.visibility = View.VISIBLE
                    }
                })
            } catch (e: Exception) {
                Log.e(TAG, "Error inicializando player: ${e.message}")
                playerCard.visibility = View.GONE
                noAudioCard.visibility = View.VISIBLE
            }
        }

        // Botón play/pause
        playPauseButton.setOnClickListener {
            animateButton(it)
            player?.let { exoPlayer ->
                if (exoPlayer.isPlaying) {
                    exoPlayer.pause()
                    isPlaying = false
                } else {
                    exoPlayer.play()
                    isPlaying = true
                    handler.post(updateProgressRunnable)
                }
                updatePlayPauseButton()
            }
        }

        // Rebobinar 10 segundos
        rewindButton.setOnClickListener {
            animateButton(it)
            player?.let { exoPlayer ->
                val newPosition = (exoPlayer.currentPosition - 10000).coerceAtLeast(0)
                exoPlayer.seekTo(newPosition)
            }
        }

        // Adelantar 10 segundos
        forwardButton.setOnClickListener {
            animateButton(it)
            player?.let { exoPlayer ->
                val newPosition = (exoPlayer.currentPosition + 10000).coerceAtMost(exoPlayer.duration)
                exoPlayer.seekTo(newPosition)
            }
        }

        // Slider de progreso
        progressSlider.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                player?.let { exoPlayer ->
                    val newPosition = (value * exoPlayer.duration / 100).toLong()
                    exoPlayer.seekTo(newPosition)
                }
            }
        }
    }

    private fun updatePlayPauseButton() {
        playPauseButton.setImageResource(
            if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play_arrow
        )
    }

    private fun animateButton(view: View) {
        view.animate()
            .scaleX(0.9f)
            .scaleY(0.9f)
            .setDuration(100)
            .withEndAction {
                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .start()
            }
            .start()
    }

    private fun formatTime(millis: Long): String {
        val totalSeconds = millis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    private fun startEntryAnimations(view: View) {
        val iconCard = view.findViewById<View>(R.id.iconCard)
        val titleText = view.findViewById<View>(R.id.songTitle)
        val categoryText = view.findViewById<View>(R.id.songCategory)
        
        // Preparar vistas
        iconCard.alpha = 0f
        iconCard.scaleX = 0.7f
        iconCard.scaleY = 0.7f
        titleText.alpha = 0f
        titleText.translationY = 20f
        categoryText.alpha = 0f
        playerCard.alpha = 0f
        playerCard.translationY = 50f

        // Animaciones
        val iconAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(iconCard, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(iconCard, View.SCALE_X, 0.7f, 1f),
                ObjectAnimator.ofFloat(iconCard, View.SCALE_Y, 0.7f, 1f)
            )
            duration = 500
            interpolator = DecelerateInterpolator()
        }

        val titleAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(titleText, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(titleText, View.TRANSLATION_Y, 20f, 0f)
            )
            duration = 400
            startDelay = 150
        }

        val categoryAnim = ObjectAnimator.ofFloat(categoryText, View.ALPHA, 0f, 1f).apply {
            duration = 300
            startDelay = 250
        }

        val playerAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(playerCard, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(playerCard, View.TRANSLATION_Y, 50f, 0f)
            )
            duration = 500
            startDelay = 400
            interpolator = DecelerateInterpolator()
        }

        AnimatorSet().apply {
            playTogether(iconAnim, titleAnim, categoryAnim, playerAnim)
            start()
        }
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
        isPlaying = false
        updatePlayPauseButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(updateProgressRunnable)
        player?.release()
        player = null
    }
}
