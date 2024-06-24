package com.darlami.analyticssdkexample.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.darlami.analyticsdk.model.SessionWithEventsAndParams
import com.darlami.analyticssdkexample.databinding.FragmentAnalyticsBinding
import com.darlami.analyticssdkexample.view_model.AnalyticsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AnalyticsFragment : Fragment() {

    private lateinit var binding: FragmentAnalyticsBinding
    private val viewModel: AnalyticsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnalyticsBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.combinedFlow.collectLatest {
                populateTable(it)
            }
        }

        return binding.root
    }

    private fun populateTable(data: List<SessionWithEventsAndParams>) {
        binding.tableLayout.removeAllViews()

        val headerRow = TableRow(requireContext())
        headerRow.addView(createTextView("Session ID", true))
        headerRow.addView(createTextView("Event Name", true))
        headerRow.addView(createTextView("Param Name", true))
        headerRow.addView(createTextView("Param Value", true))
        binding.tableLayout.addView(headerRow)

        // Populate the table with data
        data.forEach { session ->
            session.events.forEach { event ->
                event.params.forEach { param ->
                    val row = TableRow(requireContext())
                    row.addView(createTextView(session.sessionId.toString()))
                    row.addView(createTextView(event.eventName))
                    row.addView(createTextView(param.name))
                    row.addView(createTextView(param.value))
                    binding.tableLayout.addView(row)
                }
            }
        }
    }

    private fun createTextView(text: String, isHeading: Boolean = false): TextView {
        return TextView(requireContext()).apply {
            setPadding(8, 8, 8, 8)
            if (isHeading) setTypeface(null, Typeface.BOLD)
            this.text = text
        }
    }

}