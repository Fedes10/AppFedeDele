package com.example.delefede.ui.noticias

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delefede.R

class NoticiasFragment : Fragment() {

    private lateinit var noticiasRecycler: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var emptyState: LinearLayout
    private lateinit var adapter: NoticiasAdapter
    private var allNoticias: List<Noticia> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_noticias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerView()
        setupSearch()
        loadNoticias()
        startEntryAnimations(view)
    }

    private fun initViews(view: View) {
        noticiasRecycler = view.findViewById(R.id.noticiasRecycler)
        searchEditText = view.findViewById(R.id.searchEditText)
        emptyState = view.findViewById(R.id.emptyState)
    }

    private fun setupRecyclerView() {
        adapter = NoticiasAdapter { noticia ->
            // TODO: Navigate to noticia detail
        }
        noticiasRecycler.layoutManager = LinearLayoutManager(requireContext())
        noticiasRecycler.adapter = adapter
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterNoticias(s?.toString() ?: "")
            }
        })
    }

    private fun filterNoticias(query: String) {
        val filtered = if (query.isEmpty()) {
            allNoticias
        } else {
            allNoticias.filter { noticia ->
                noticia.title.contains(query, ignoreCase = true) ||
                noticia.description.contains(query, ignoreCase = true) ||
                noticia.category.contains(query, ignoreCase = true)
            }
        }
        adapter.submitList(filtered)
        emptyState.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
        noticiasRecycler.visibility = if (filtered.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun loadNoticias() {
        // Noticias de ejemplo de la Delegación
        allNoticias = listOf(
            Noticia(
                id = 1,
                title = "Recorrido de la procesión de la Inmaculada Concepción",
                description = "Descubre el recorrido completo de la procesión de la Inmaculada Concepción por las calles de Córdoba. Un momento único de devoción mariana.",
                date = "8 de diciembre, 2024",
                category = "Inmaculada",
                imageRes = R.drawable.ic_marian,
                isFeatured = true
            ),
            Noticia(
                id = 2,
                title = "Vigilia de la Inmaculada 2024",
                description = "La Vigilia de la Inmaculada reunió a cientos de jóvenes de toda la diócesis en una noche de oración, cantos y alegría compartida.",
                date = "7 de diciembre, 2024",
                category = "Evento",
                imageRes = R.drawable.ic_calendar,
                isFeatured = true
            ),
            Noticia(
                id = 3,
                title = "Guadalupe XXIX - Fotogalería",
                description = "Revive los mejores momentos de la XXIX edición de la Peregrinación a Guadalupe a través de esta galería fotográfica.",
                date = "15 de octubre, 2024",
                category = "Guadalupe",
                imageRes = R.drawable.ic_guadalupe,
                isFeatured = false
            ),
            Noticia(
                id = 4,
                title = "Inscripciones abiertas para Guadalupe XXX",
                description = "Ya están abiertas las inscripciones para la próxima edición de la Peregrinación a Guadalupe. ¡No te quedes sin tu plaza!",
                date = "1 de septiembre, 2024",
                category = "Guadalupe",
                imageRes = R.drawable.ic_guadalupe,
                isFeatured = true
            ),
            Noticia(
                id = 5,
                title = "Encuentro Diocesano de Jóvenes 2024",
                description = "Un fin de semana de convivencia, formación y oración para los jóvenes de nuestra diócesis.",
                date = "20 de mayo, 2024",
                category = "Evento",
                imageRes = R.drawable.ic_community,
                isFeatured = false
            ),
            Noticia(
                id = 6,
                title = "Peregrinación a Fátima - Memoria 2024",
                description = "Recordamos los momentos más significativos de nuestra peregrinación a Fátima de este año.",
                date = "13 de mayo, 2024",
                category = "Fátima",
                imageRes = R.drawable.ic_fatima,
                isFeatured = false
            ),
            Noticia(
                id = 7,
                title = "Nuevo cancionero disponible",
                description = "Hemos actualizado el cancionero con nuevas canciones para este curso pastoral. Descárgalo desde la app.",
                date = "1 de octubre, 2024",
                category = "Cancionero",
                imageRes = R.drawable.ic_music,
                isFeatured = false
            ),
            Noticia(
                id = 8,
                title = "Carta del Obispo a los jóvenes",
                description = "Don Jesús dirige unas palabras de aliento y esperanza a todos los jóvenes de la diócesis de Córdoba.",
                date = "15 de agosto, 2024",
                category = "Mensaje",
                imageRes = R.drawable.ic_cross,
                isFeatured = true
            )
        )
        adapter.submitList(allNoticias)
    }

    private fun startEntryAnimations(view: View) {
        val title = view.findViewById<View>(R.id.noticiasTitle)
        val searchCard = view.findViewById<View>(R.id.searchCard)
        
        // Preparar vistas
        title.alpha = 0f
        title.translationY = -30f
        searchCard.alpha = 0f
        searchCard.translationY = 20f
        noticiasRecycler.alpha = 0f

        // Animaciones
        val titleAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(title, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(title, View.TRANSLATION_Y, -30f, 0f)
            )
            duration = 400
            interpolator = DecelerateInterpolator()
        }

        val searchAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(searchCard, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(searchCard, View.TRANSLATION_Y, 20f, 0f)
            )
            duration = 400
            interpolator = DecelerateInterpolator()
            startDelay = 150
        }

        val recyclerAnim = ObjectAnimator.ofFloat(noticiasRecycler, View.ALPHA, 0f, 1f).apply {
            duration = 500
            startDelay = 300
        }

        AnimatorSet().apply {
            playTogether(titleAnim, searchAnim, recyclerAnim)
            start()
        }
    }
}

// Data class para las noticias
data class Noticia(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val category: String,
    val imageRes: Int,
    val isFeatured: Boolean = false
)
