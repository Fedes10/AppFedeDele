package com.example.delefede.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delefede.R
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {
    
    private lateinit var monthYearText: TextView
    private lateinit var calendarGrid: RecyclerView
    private lateinit var eventsRecycler: RecyclerView
    private lateinit var selectedDateText: TextView
    private lateinit var emptyEventsState: LinearLayout
    
    private var currentCalendar = Calendar.getInstance()
    private var selectedDate = Calendar.getInstance()
    
    private val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale("es", "ES"))
    private val dayFormat = SimpleDateFormat("EEEE, d 'de' MMMM", Locale("es", "ES"))
    
    // Eventos de ejemplo
    private val events = mapOf(
        // Fátima 2025
        "2025-02-20" to listOf(CalendarEvent("Salida Peregrinación Fátima", "06:00", "Salida desde Córdoba", "#2196F3")),
        "2025-02-21" to listOf(CalendarEvent("Peregrinación Fátima", "Todo el día", "Fátima, Portugal", "#2196F3")),
        "2025-02-22" to listOf(CalendarEvent("Regreso Peregrinación Fátima", "18:00", "Llegada a Córdoba", "#2196F3")),
        // Guadalupe 2025
        "2025-09-06" to listOf(CalendarEvent("Salida Peregrinación Guadalupe", "06:00", "Salida desde Córdoba", "#4CAF50")),
        "2025-09-07" to listOf(CalendarEvent("Peregrinación Guadalupe", "Todo el día", "Guadalupe, Cáceres", "#4CAF50")),
        "2025-09-08" to listOf(CalendarEvent("Regreso Peregrinación Guadalupe", "18:00", "Llegada a Córdoba", "#4CAF50")),
        // Inmaculada 2025
        "2025-12-07" to listOf(CalendarEvent("Peregrinación Inmaculada", "Todo el día", "Córdoba", "#9C27B0")),
        "2025-12-08" to listOf(CalendarEvent("Fiesta Inmaculada", "Todo el día", "Córdoba", "#9C27B0"))
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupCalendar()
        setupEventCards(view)
        updateMonthYear()
        updateCalendarGrid()
        updateSelectedDateEvents()
    }
    
    private fun initViews(view: View) {
        monthYearText = view.findViewById(R.id.monthYearText)
        calendarGrid = view.findViewById(R.id.calendarGrid)
        eventsRecycler = view.findViewById(R.id.eventsRecycler)
        selectedDateText = view.findViewById(R.id.selectedDateText)
        emptyEventsState = view.findViewById(R.id.emptyEventsState)
        
        view.findViewById<ImageButton>(R.id.btnPreviousMonth).setOnClickListener {
            currentCalendar.add(Calendar.MONTH, -1)
            updateMonthYear()
            updateCalendarGrid()
        }
        
        view.findViewById<ImageButton>(R.id.btnNextMonth).setOnClickListener {
            currentCalendar.add(Calendar.MONTH, 1)
            updateMonthYear()
            updateCalendarGrid()
        }
    }
    
    private fun setupCalendar() {
        calendarGrid.layoutManager = GridLayoutManager(requireContext(), 7)
        eventsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }
    
    private fun setupEventCards(view: View) {
        view.findViewById<MaterialCardView>(R.id.cardFatima).setOnClickListener {
            findNavController().navigate(R.id.fatimaFragment)
        }
        
        view.findViewById<MaterialCardView>(R.id.cardGuadalupe).setOnClickListener {
            findNavController().navigate(R.id.guadalupeFragment)
        }
        
        view.findViewById<MaterialCardView>(R.id.cardInmaculada).setOnClickListener {
            findNavController().navigate(R.id.inmaculadaFragment)
        }
    }
    
    private fun updateMonthYear() {
        val monthName = monthYearFormat.format(currentCalendar.time)
        monthYearText.text = monthName.replaceFirstChar { it.uppercase() }
    }
    
    private fun updateCalendarGrid() {
        val days = generateCalendarDays()
        calendarGrid.adapter = CalendarDayAdapter(days) { day ->
            if (day.dayNumber > 0) {
                selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR))
                    set(Calendar.MONTH, currentCalendar.get(Calendar.MONTH))
                    set(Calendar.DAY_OF_MONTH, day.dayNumber)
                }
                updateCalendarGrid()
                updateSelectedDateEvents()
            }
        }
    }
    
    private fun generateCalendarDays(): List<CalendarDay> {
        val days = mutableListOf<CalendarDay>()
        
        val calendar = currentCalendar.clone() as Calendar
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        
        // Ajustar el primer día (Lunes = 1)
        var firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2
        if (firstDayOfWeek < 0) firstDayOfWeek = 6
        
        // Añadir días vacíos
        repeat(firstDayOfWeek) {
            days.add(CalendarDay(0, false, false, false))
        }
        
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val today = Calendar.getInstance()
        
        for (day in 1..daysInMonth) {
            val isToday = currentCalendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    currentCalendar.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                    day == today.get(Calendar.DAY_OF_MONTH)
            
            val isSelected = currentCalendar.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR) &&
                    currentCalendar.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH) &&
                    day == selectedDate.get(Calendar.DAY_OF_MONTH)
            
            val dateKey = String.format("%04d-%02d-%02d", 
                currentCalendar.get(Calendar.YEAR),
                currentCalendar.get(Calendar.MONTH) + 1,
                day)
            val hasEvent = events.containsKey(dateKey)
            
            days.add(CalendarDay(day, isToday, isSelected, hasEvent))
        }
        
        return days
    }
    
    private fun updateSelectedDateEvents() {
        val dateKey = String.format("%04d-%02d-%02d",
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH) + 1,
            selectedDate.get(Calendar.DAY_OF_MONTH))
        
        val dayEvents = events[dateKey] ?: emptyList()
        
        val today = Calendar.getInstance()
        val isToday = selectedDate.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                selectedDate.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                selectedDate.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)
        
        selectedDateText.text = if (isToday) {
            "Eventos de hoy"
        } else {
            "Eventos del ${dayFormat.format(selectedDate.time)}"
        }
        
        if (dayEvents.isEmpty()) {
            eventsRecycler.visibility = View.GONE
            emptyEventsState.visibility = View.VISIBLE
        } else {
            eventsRecycler.visibility = View.VISIBLE
            emptyEventsState.visibility = View.GONE
            eventsRecycler.adapter = EventAdapter(dayEvents)
        }
    }
    
    data class CalendarDay(
        val dayNumber: Int,
        val isToday: Boolean,
        val isSelected: Boolean,
        val hasEvent: Boolean
    )
    
    data class CalendarEvent(
        val title: String,
        val time: String,
        val location: String,
        val color: String
    )
    
    // Adapter para días del calendario
    inner class CalendarDayAdapter(
        private val days: List<CalendarDay>,
        private val onDayClick: (CalendarDay) -> Unit
    ) : RecyclerView.Adapter<CalendarDayAdapter.DayViewHolder>() {
        
        inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val dayNumber: TextView = view.findViewById(R.id.dayNumber)
            val selectedBackground: View = view.findViewById(R.id.selectedBackground)
            val eventIndicator: View = view.findViewById(R.id.eventIndicator)
        }
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_calendar_day, parent, false)
            return DayViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
            val day = days[position]
            
            if (day.dayNumber > 0) {
                holder.dayNumber.text = day.dayNumber.toString()
                holder.dayNumber.visibility = View.VISIBLE
                
                when {
                    day.isSelected -> {
                        holder.selectedBackground.visibility = View.VISIBLE
                        holder.selectedBackground.setBackgroundResource(R.drawable.bg_calendar_day_selected)
                        holder.dayNumber.setTextColor(resources.getColor(R.color.white, null))
                    }
                    day.isToday -> {
                        holder.selectedBackground.visibility = View.VISIBLE
                        holder.selectedBackground.setBackgroundResource(R.drawable.bg_calendar_day_today)
                        holder.dayNumber.setTextColor(resources.getColor(R.color.orange_primary, null))
                    }
                    else -> {
                        holder.selectedBackground.visibility = View.GONE
                        holder.dayNumber.setTextColor(resources.getColor(R.color.text_primary, null))
                    }
                }
                
                holder.eventIndicator.visibility = if (day.hasEvent && !day.isSelected) View.VISIBLE else View.GONE
                
                holder.itemView.setOnClickListener { onDayClick(day) }
            } else {
                holder.dayNumber.visibility = View.INVISIBLE
                holder.selectedBackground.visibility = View.GONE
                holder.eventIndicator.visibility = View.GONE
            }
        }
        
        override fun getItemCount() = days.size
    }
    
    // Adapter para eventos
    inner class EventAdapter(
        private val events: List<CalendarEvent>
    ) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
        
        inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.eventTitle)
            val time: TextView = view.findViewById(R.id.eventTime)
            val location: TextView = view.findViewById(R.id.eventLocation)
            val colorIndicator: View = view.findViewById(R.id.eventColorIndicator)
        }
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
            return EventViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
            val event = events[position]
            holder.title.text = event.title
            holder.time.text = event.time
            
            if (event.location.isNotEmpty()) {
                holder.location.text = event.location
                holder.location.visibility = View.VISIBLE
            } else {
                holder.location.visibility = View.GONE
            }
            
            try {
                val color = android.graphics.Color.parseColor(event.color)
                holder.colorIndicator.setBackgroundColor(color)
            } catch (e: Exception) {
                // Keep default color
            }
        }
        
        override fun getItemCount() = events.size
    }
}
