package com.example.delefede.ui.menu

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.button.MaterialButton
import com.example.delefede.R
import androidx.navigation.fragment.findNavController

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    
    private var dismissListener: (() -> Unit)? = null
    
    fun setOnDismissListener(listener: () -> Unit) {
        dismissListener = listener
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Estilo del BottomSheet con esquinas redondeadas
        setStyle(STYLE_NORMAL, R.style.Theme_DeleFede_BottomSheet)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_menu, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Configurar comportamiento del bottom sheet
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
            isDraggable = true
        }
        
        val navController = requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.firstOrNull()
            ?.let { (it as? androidx.navigation.fragment.NavHostFragment)?.navController }
            ?: findNavController()
        
        // Inicio
        view.findViewById<MaterialCardView>(R.id.menuHomeCard)?.setOnClickListener {
            navController.navigate(R.id.homeFragment)
            dismiss()
        }
        
        // Cancionero
        view.findViewById<MaterialCardView>(R.id.menuCancioneroCard)?.setOnClickListener {
            navController.navigate(R.id.deleFragment)
            dismiss()
        }
        
        // Noticias
        view.findViewById<MaterialCardView>(R.id.menuNoticiasCard)?.setOnClickListener {
            navController.navigate(R.id.noticiasFragment)
            dismiss()
        }
        
        // Guadalupe
        view.findViewById<MaterialCardView>(R.id.menuGuadalupeCard)?.setOnClickListener {
            navController.navigate(R.id.guadalupeFragment)
            dismiss()
        }
        
        // Fátima
        view.findViewById<MaterialCardView>(R.id.menuFatimaCard)?.setOnClickListener {
            navController.navigate(R.id.fatimaFragment)
            dismiss()
        }
        
        // Inmaculada
        view.findViewById<MaterialCardView>(R.id.menuInmaculadaCard)?.setOnClickListener {
            navController.navigate(R.id.inmaculadaFragment)
            dismiss()
        }
        
        // Contacto
        view.findViewById<MaterialCardView>(R.id.menuContactCard)?.setOnClickListener {
            navController.navigate(R.id.contactFragment)
            dismiss()
        }
        
        // Acerca de
        view.findViewById<MaterialCardView>(R.id.menuAboutCard)?.setOnClickListener {
            navController.navigate(R.id.aboutFragment)
            dismiss()
        }
        
        // Login
        view.findViewById<MaterialButton>(R.id.menuLoginButton)?.setOnClickListener {
            navController.navigate(R.id.loginFragment)
            dismiss()
        }
    }
    
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.invoke()
    }
}
