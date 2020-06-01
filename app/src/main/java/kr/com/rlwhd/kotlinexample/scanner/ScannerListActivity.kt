package kr.com.rlwhd.kotlinexample.scanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.content_todo_list.*
import kr.com.rlwhd.kotlinexample.R
import kr.com.rlwhd.kotlinexample.data.TodoData
import kr.com.rlwhd.kotlinexample.example.todo.EditActivity
import kr.com.rlwhd.kotlinexample.example.todo.TodoListAdapter
import kr.com.rlwhd.kotlinexample.scanner.ScannerActivity
import org.jetbrains.anko.startActivity

class ScannerListActivity : AppCompatActivity() {
    val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_list)
        setSupportActionBar(toolbar)

        // 전체 할 일 정보를 가져와서 날짜순으로 내림차순 정렬렬
        val realmResult = realm.where<TodoData>().findAll().sort("date", Sort.DESCENDING)
        val adapter = TodoListAdapter(realmResult)
        lv_todo_list.adapter = adapter

        realmResult.addChangeListener { _ -> adapter.notifyDataSetChanged() }

        lv_todo_list.setOnItemClickListener { parent, view, position, id ->
            // 할 일 수정
            startActivity<EditActivity>("id" to id)
        }

        // 새 할 일 추가
        fab_todo_add.setOnClickListener {
            startActivity<ScannerActivity>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}
