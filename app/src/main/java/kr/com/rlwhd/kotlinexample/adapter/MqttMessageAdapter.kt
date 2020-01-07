package kr.com.rlwhd.kotlinexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.com.rlwhd.kotlinexample.R
import kr.com.rlwhd.kotlinexample.data.MqttData

class MqttMessageAdapter(val context: Context, val msgList: ArrayList<MqttData>): RecyclerView.Adapter<MqttMessageAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv_video, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(msgList[position], context)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val videoText = itemView?.findViewById<TextView>(R.id.tv_item_video)

        fun bind (msg: MqttData, context: Context) {
            videoText?.text = msg.text
        }
    }


}