package com.example.duty_timer.screens.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.duty_timer.databinding.ItemEventBinding
import com.example.duty_timer.model.events.entities.EventEntity
import com.example.duty_timer.screens.datePicker.DateForPicker
import java.util.Calendar

data class Event(
    val id: Int,
    val title: String,
    val date: DateForPicker
)

interface EventItemListener {
    fun onLongClick(id: Int)
}

class EventsAdapter(
    private val eventItemListener: EventItemListener
) : RecyclerView.Adapter<EventsAdapter.EventHolder>() {

    var events = listOf<Event>()

    inner class EventHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEventBinding.inflate(inflater, parent, false)

        return EventHolder(binding)
    }


    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val binding = holder.binding

        binding.eventItemTitle.text = events[position].title
        binding.eventItemDate.text = getFormattedDate(events[position].date)

        binding.root.setOnLongClickListener {
            eventItemListener.onLongClick(events[position].id)
            true
        }
    }

    override fun getItemCount(): Int = events.size

    fun map(eventEntities : List<EventEntity>) : List<Event> {
        return eventEntities.map {
            convertEventEntityToEvent(it)
        }
    }

    private fun convertEventEntityToEvent(entity: EventEntity) : Event {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = entity.date
        val date = DateForPicker(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        return Event(entity.id, entity.title, date)
    }

    private fun getMonthFromIndex(monthIndex: Int): String {
        val months = arrayOf("Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентябра", "Октября", "Ноября", "Декабря")
        return months[monthIndex]
    }

    private fun getFormattedDate(date: DateForPicker) : String {
        return "${date.day} ${getMonthFromIndex(date.month)}, ${date.year}"
    }

}