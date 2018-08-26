package ckoos.windows_volume_remote

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ckoos.windows_volume_remote.sync.VolumeApplication
import android.widget.TextView
import org.w3c.dom.Text

class ApplicationAdapter(private val dataSource: ArrayList<VolumeApplication>) : RecyclerView.Adapter<ApplicationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationAdapter.ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)

        val listItem = inflater.inflate(R.layout.application_row, parent, false)




        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setName("TESTTT")
    }

    fun addItem(item: VolumeApplication) {
        this.dataSource.add(item)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var textView = view.findViewById<View>(R.id.textView2) as TextView

        fun setName(string: String) {
            this.textView.setText(string)
        }
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}