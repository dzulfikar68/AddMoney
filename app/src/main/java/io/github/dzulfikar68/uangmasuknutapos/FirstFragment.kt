package io.github.dzulfikar68.uangmasuknutapos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.dzulfikar68.uangmasuknutapos.db.MappingHelper
import io.github.dzulfikar68.uangmasuknutapos.item.ItemAdapter
import io.github.dzulfikar68.uangmasuknutapos.model.MoneyHelper
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var adapter: ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ItemAdapter(requireContext())
        adapter?.list = arrayListOf()
        mainRecyclerView?.setView(adapter)
        loadNotesAsync()
    }

    private fun RecyclerView?.setView(adapter: ItemAdapter?) {
        this?.layoutManager = LinearLayoutManager(this?.context)
        this?.hasFixedSize()
        this?.adapter = adapter
    }

    private var noteHelper: MoneyHelper? = null
    private fun loadNotesAsync() {
        noteHelper = MoneyHelper.getInstance(requireContext())
        noteHelper?.open()
        //FROM UI
//        val cursor = noteHelper?.queryAll()
//        val list = MappingHelper.mapCursorToArrayList(cursor)

        //FROM COROUTINE
        GlobalScope.launch(Dispatchers.Main) {
//            progressbar.visibility = View.VISIBLE
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = noteHelper?.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
//            progressbar.visibility = View.INVISIBLE
            val arrayThis = deferredNotes.await()
            if (arrayThis.size > 0) {
//                adapter.listNotes = notes
                adapter?.list = arrayThis
                adapter?.notifyDataSetChanged()
//                Toast.makeText(context, arrayThis.toString(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "tidak ada", Toast.LENGTH_LONG).show()
//                adapter.listNotes = ArrayList()
//                showSnackbarMessage("Tidak ada data saat ini")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        noteHelper?.close()
    }
}