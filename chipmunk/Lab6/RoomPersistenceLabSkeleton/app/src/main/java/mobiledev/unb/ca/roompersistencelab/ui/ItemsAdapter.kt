package mobiledev.unb.ca.roompersistencelab.ui

import android.content.Context
import android.widget.ArrayAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import mobiledev.unb.ca.roompersistencelab.R
import android.widget.TextView
import mobiledev.unb.ca.roompersistencelab.entity.Item

class ItemsAdapter(context: Context, items: List<Item>) : ArrayAdapter<Item>(
    context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get the data item for this position
        val item = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view
        var currView: View
        if (convertView == null) {
            currView = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false)
        } else {
            currView = convertView
        }

        // Lookup view for data population
        val textViewName: TextView = currView.findViewById(R.id.item_textview)
        val textViewNum: TextView = currView.findViewById(R.id.num_textview)

        if (item != null) {
            // TODO
            //  Set the text used by textViewName and textViewNum using the data object
            //  This will need to updated once the entity model has been updated
        }
        
        // Return the completed view to render on screen
        return currView
    }
}