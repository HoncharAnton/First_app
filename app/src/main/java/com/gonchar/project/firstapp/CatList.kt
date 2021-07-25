package com.gonchar.project.firstapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gonchar.project.firstapp.cat_list.CatListGridAdapter
import com.gonchar.project.firstapp.cat_list.CatListRecAdapter
import com.gonchar.project.firstapp.databinding.ActivityCatListBinding

class CatList : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCatListBinding
    private val titleList = ArrayList<String>()
    private val addressList = ArrayList<String>()
    private var switch : Byte = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatListBinding.inflate(layoutInflater)
        readFileAsLines("cat.txt")
        setListener()
        makeRecViewAdapter()
        setContentView(binding.root)
    }

    /**
     * setListener method initializes listener for some UI elements
     */
    private fun setListener() {
        binding.btnList.setOnClickListener(this)
    }

    /**
     * makeRecViewAdapter method create and set adapter for the recycler view
     */
    private fun makeRecViewAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        val adapter = CatListRecAdapter(titleList,addressList,this)
        binding.recyclerView.adapter = adapter
    }

    /**
     * makeGridViewAdapter method create and set adapter for the grid view
     */
    private fun makeGridViewAdapter(){
        val adapter = CatListGridAdapter(this, addressList)
        binding.gridView.adapter = adapter
    }

    /**
     * readFileAsLine method read file line by line from first to last line
     * @param fileName name of the file for reading
     */
    private fun readFileAsLines(fileName: String) {
        val bufferedReader = assets.open(fileName).bufferedReader()
        bufferedReader.useLines { lines -> lines.forEach { lineParser(it) } }
    }

    /**
     * lineParser method parses and separated the argument into two values
     * (cat breed name and link for cat breed photo), using the symbol "|"
     * as the separation symbol.
     * @param line single line from file "cat.txt"
     */
    private fun lineParser(line: String) {
        val temp : List<String> = line.split("|")
        titleList.add(temp.first())
        addressList.add(temp.last())
    }

    /**
     * onClick realizes click logic for some UI elements
     * @param v - view, which was clicked
     */
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnList -> {
                if (switch == 0.toByte()){
                    switch = 1
                    makeGridViewAdapter()
                    viewSwitcher(binding.recyclerView,binding.gridView)
                }else{
                    switch = 0
                    makeRecViewAdapter()
                    viewSwitcher(binding.gridView,binding.recyclerView)
                }
            }
        }
    }

    /**
     * viewSwitcher method changes view (changes visibility parameters of the views)
     * @param offView - View for turn off
     * @param onView - View for turn on
     */
    private fun viewSwitcher(offView : View, onView : View) {
        offView.visibility = View.GONE
        onView.visibility = View.VISIBLE
    }
}