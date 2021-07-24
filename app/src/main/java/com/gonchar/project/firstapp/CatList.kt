package com.gonchar.project.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gonchar.project.firstapp.cat_list.CatListRecAdapter
import com.gonchar.project.firstapp.databinding.ActivityCatListBinding

class CatList : AppCompatActivity() {

    lateinit var binding: ActivityCatListBinding
    private val titleList = ArrayList<String>()
    private val addressList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatListBinding.inflate(layoutInflater)
        readFileAsLines("cat.txt")

        makeRecViewAdapter()
        setContentView(binding.root)


    }

    private fun makeRecViewAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        val adapter = CatListRecAdapter(titleList,addressList,this)
        binding.recyclerView.adapter = adapter
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

}