package kr.com.rlwhd.kotlinexample.example.todo

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter
import kr.com.rlwhd.kotlinexample.R
import kr.com.rlwhd.kotlinexample.data.TodoData

class TodoListAdapter(realmResult: OrderedRealmCollection<TodoData>) : RealmBaseAdapter<TodoData>(realmResult) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val vh: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_todo, parent, false)

            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        if (adapterData != null) {
            val item = adapterData!![position]
            vh.tv_item_date.text = item.title
            vh.tv_item_todo.text = DateFormat.format("yyyy/MM/dd", item.date)
        }

        return view
    }

    override fun getItemId(position: Int): Long {
        if (adapterData != null) {
            return adapterData!![position].id
        }
        return super.getItemId(position)
    }

    class ViewHolder(view: View) {
        val tv_item_todo: TextView = view.findViewById(R.id.tv_item_todo)
        val tv_item_date: TextView = view.findViewById(R.id.tv_item_date)
    }
}