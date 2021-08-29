package com.agromall.tellerium

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.agromall.tellerium.databinding.FragmentReportBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import timber.log.Timber


class ReportFragment : Fragment() {
    private var _binding: FragmentReportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val entries by lazy {
        val list = ArrayList<Entry>()
        for (i in 1..30) {
            list.add(Entry(i.toFloat(), (1..99).random().toFloat()))
        }
        list
    }
    val currentMonth = "Aug "
    val labels by lazy {
        val list = ArrayList<String>()
        for (i in 1..30) {
            list.add(currentMonth + i)
        }
        list
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupChart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun FragmentReportBinding.setupChart() {
//        setup data

        val dataSet = LineDataSet(entries, "").apply {
            setDrawValues(false)
            setDrawFilled(false)
            lineWidth = 5f
            color = ContextCompat.getColor(requireContext(), R.color.green)
            mode = LineDataSet.Mode.CUBIC_BEZIER

            highLightColor = ContextCompat.getColor(requireContext(), R.color.green_dark)
            highlightLineWidth = 2f

            setDrawHorizontalHighlightIndicator(false)
            setDrawCircles(false)
        }


        chart.apply {
            xAxis.labelRotationAngle = 0f
            data = LineData(dataSet)

            //format chart
            axisRight.isEnabled = false
            legend.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            axisLeft.setDrawGridLines(true)
            axisLeft.gridColor = ContextCompat.getColor(requireContext(), R.color.light_gray)

            val colorLight = ContextCompat.getColor(requireContext(), R.color.light_green)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            getRenderer().getPaintRender().setShadowLayer(3f, 20f, 20f, colorLight)

            axisRight.setDrawGridLines(false)
            axisLeft.setDrawAxisLine(false)
            setTouchEnabled(true)
            setPinchZoom(true)

            description.isEnabled = false
            setNoDataText("No data yet!")
            animateX(500, Easing.EaseInExpo)
            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry, h: Highlight) {
                    entries.forEachIndexed { index, entry ->
                        if (entry.x == e.x && entry.y == e.y) {
                            entry.icon =
                                ContextCompat.getDrawable(requireContext(), R.drawable.ic_chart_indicator)
                        } else {
                            entry.icon = null
                        }
                    }
                    notifyDataSetChanged()
                }

                override fun onNothingSelected() {}
            })

            //format axis values
            val formatter: ValueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    if (value.toInt() >= 0 && value.toInt() < labels.size) {
                        return labels[value.toInt()]
                    } else {
                        return ""
                    }
                }
            }

            val formatterY: ValueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return "₦${value.toInt()}"
                }
            }
            xAxis.granularity = 1f // minimum axis-step (interval) is 1
            xAxis.valueFormatter = formatter
            chart.setVisibleXRangeMaximum(5f)
            axisLeft.valueFormatter = formatterY
            axisLeft.setLabelCount(5, true)
            axisLeft.axisMinimum = 0f
            axisLeft.axisMaximum = 100f

        }

        //Custom marker view

        val markerView = CustomMarker(requireContext(), R.layout.marker_view)
        chart.marker = markerView
    }

    companion object {
        var TAG = ReportFragment::class.java.simpleName
        fun make(
        ): Fragment {
            val contributeFragment = ReportFragment()
            return contributeFragment
        }
    }

    inner class CustomMarker(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {
        val topText by lazy {
            findViewById<TextView>(R.id.tvPrice);
        }
        val lowerText by lazy {
            findViewById<TextView>(R.id.txView);
        }

        override fun onViewAdded(child: View?) {
            super.onViewAdded(child)

        }

        override fun refreshContent(entry: Entry?, highlight: Highlight?) {
            entries.forEachIndexed { index, data ->
                if (data.x == entry?.x && data.y == entry.y) {
                    topText.text = "${labels[index]}, 2021"
                }
            }
            lowerText.text = "₦${entry?.y}"
            super.refreshContent(entry, highlight)
        }

        override fun getOffsetForDrawingAtPoint(xpos: Float, ypos: Float): MPPointF {
            return MPPointF(-width / 2f, -height - 10f)
        }
    }
}

class MyYAxisFormatter : ValueFormatter() {
    val amounts = arrayOf(0f, 25f, 50f, 75f, 100f)
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        axis?.setLabelCount(6, true)
        var index = value
        if (index < 0 ){
            return "₦$value"
        }

        return amounts[index.toInt()].toString()
        //return amounts.getOrNull(value.toInt()) ?: value.toFl
    }
}

