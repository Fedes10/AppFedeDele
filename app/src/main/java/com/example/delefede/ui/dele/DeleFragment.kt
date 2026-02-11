package com.example.delefede.ui.dele

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.LinearProgressIndicator
import android.widget.TextView
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.delefede.R
import com.example.delefede.data.model.Cancion

class DeleFragment : Fragment() {
    
    private lateinit var viewModel: DeleViewModel
    private lateinit var adapter: CancionAdapter
    private lateinit var emptyState: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private var loadingIndicator: ProgressBar? = null
    
    // Mini player views
    private lateinit var miniPlayerCard: MaterialCardView
    private lateinit var miniPlayerTitle: TextView
    private lateinit var miniPlayerArtist: TextView
    private lateinit var miniPlayerProgress: LinearProgressIndicator
    private lateinit var btnPlayPause: FloatingActionButton
    private lateinit var btnPrevious: ImageButton
    private lateinit var btnNext: ImageButton
    
    private var currentSong: Cancion? = null
    private var isPlaying = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dele, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inicializar ViewModel con Application context
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[DeleViewModel::class.java]
        
        initViews(view)
        setupRecyclerView()
        setupSearch(view)
        setupCategoryChips(view)
        setupMiniPlayer()
        observeViewModel()
        startEntryAnimations(view)
    }

    private fun initViews(view: View) {
        emptyState = view.findViewById(R.id.emptyState)
        recyclerView = view.findViewById(R.id.songsRecycler)
        loadingIndicator = view.findViewById(R.id.loadingIndicator)
        
        // Mini player
        miniPlayerCard = view.findViewById(R.id.miniPlayerCard)
        miniPlayerTitle = view.findViewById(R.id.miniPlayerTitle)
        miniPlayerArtist = view.findViewById(R.id.miniPlayerArtist)
        miniPlayerProgress = view.findViewById(R.id.miniPlayerProgress)
        btnPlayPause = view.findViewById(R.id.btnPlayPause)
        btnPrevious = view.findViewById(R.id.btnPrevious)
        btnNext = view.findViewById(R.id.btnNext)
    }

    private fun setupRecyclerView() {
        adapter = CancionAdapter(emptyList()) { cancion ->
            // Mostrar la letra de la canción
            val bundle = Bundle().apply {
                putString("titulo", cancion.titulo)
                putString("letra", cancion.letra)
                putString("categoria", cancion.categoria)
                putString("artista", cancion.artista)
                putString("audioUrl", cancion.audioUrl)
                putString("audioLocal", cancion.audioLocal)
            }
            findNavController().navigate(R.id.action_deleFragment_to_leerCancionFragment, bundle)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun setupSearch(view: View) {
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
        
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.buscarCanciones(s?.toString() ?: "")
            }
        })
    }

    private fun setupCategoryChips(view: View) {
        val chipGroup = view.findViewById<ChipGroup>(R.id.categoryChipGroup)
        
        // Configurar listeners para los chips existentes en el layout
        view.findViewById<Chip>(R.id.chipAll)?.setOnClickListener { 
            viewModel.mostrarTodas()
            adapter.resetAnimations()
        }
        
        view.findViewById<Chip>(R.id.chipDele)?.setOnClickListener { 
            viewModel.filtrarPorCategoria("La Dele")
            adapter.resetAnimations()
        }
        
        view.findViewById<Chip>(R.id.chipHakuna)?.setOnClickListener { 
            viewModel.filtrarPorCategoria("Hakuna")
            adapter.resetAnimations()
        }
        
        view.findViewById<Chip>(R.id.chipAdoracion)?.setOnClickListener { 
            viewModel.filtrarPorCategoria("Adoración")
            adapter.resetAnimations()
        }
        
        view.findViewById<Chip>(R.id.chipMariano)?.setOnClickListener { 
            viewModel.filtrarPorCategoria("Mariano")
            adapter.resetAnimations()
        }
        
        view.findViewById<Chip>(R.id.chipTradicional)?.setOnClickListener { 
            viewModel.filtrarPorCategoria("Tradicional")
            adapter.resetAnimations()
        }
    }
    
    private fun setupMiniPlayer() {
        btnPlayPause.setOnClickListener {
            togglePlayPause()
        }
        
        btnPrevious.setOnClickListener {
            playPreviousSong()
        }
        
        btnNext.setOnClickListener {
            playNextSong()
        }
        
        miniPlayerCard.setOnClickListener {
            currentSong?.let { song ->
                val bundle = Bundle().apply {
                    putString("titulo", song.titulo)
                    putString("letra", song.letra)
                    putString("categoria", song.categoria)
                    putString("artista", song.artista)
                    putString("audioUrl", song.audioUrl)
                    putString("audioLocal", song.audioLocal)
                }
                findNavController().navigate(R.id.action_deleFragment_to_leerCancionFragment, bundle)
            }
        }
    }
    
    private fun showMiniPlayer(cancion: Cancion) {
        currentSong = cancion
        miniPlayerTitle.text = cancion.titulo
        miniPlayerArtist.text = "${cancion.artista} • ${cancion.categoria}"
        
        if (miniPlayerCard.visibility != View.VISIBLE) {
            miniPlayerCard.visibility = View.VISIBLE
            miniPlayerCard.alpha = 0f
            miniPlayerCard.translationY = 100f
            miniPlayerCard.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(300)
                .start()
        }
    }
    
    private fun togglePlayPause() {
        isPlaying = !isPlaying
        btnPlayPause.setImageResource(
            if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        )
    }
    
    private fun playPreviousSong() {
        // TODO: Implementar
    }
    
    private fun playNextSong() {
        // TODO: Implementar
    }

    private fun observeViewModel() {
        // Observar canciones desde Room (sincronizado con GitHub)
        viewModel.canciones.observe(viewLifecycleOwner) { canciones ->
            adapter.updateList(canciones)
            updateEmptyState(canciones.isEmpty())
        }
        
        // Observar estado de carga
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            loadingIndicator?.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        // Observar errores
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        emptyState.visibility = if (isEmpty) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    private fun startEntryAnimations(view: View) {
        val searchCard = view.findViewById<View>(R.id.searchCard)
        val chipGroup = view.findViewById<ChipGroup>(R.id.categoryChipGroup)
        
        // Preparar vistas
        searchCard.alpha = 0f
        searchCard.translationY = 20f
        chipGroup.alpha = 0f
        recyclerView.alpha = 0f

        val searchAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(searchCard, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(searchCard, View.TRANSLATION_Y, 20f, 0f)
            )
            duration = 400
            interpolator = DecelerateInterpolator()
            startDelay = 100
        }

        val chipAnim = ObjectAnimator.ofFloat(chipGroup, View.ALPHA, 0f, 1f).apply {
            duration = 300
            startDelay = 200
        }

        val recyclerAnim = ObjectAnimator.ofFloat(recyclerView, View.ALPHA, 0f, 1f).apply {
            duration = 400
            startDelay = 300
        }

        AnimatorSet().apply {
            playTogether(searchAnim, chipAnim, recyclerAnim)
            start()
        }
    }
}
