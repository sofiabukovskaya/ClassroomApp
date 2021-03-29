package com.example.classroomapp.student.studentInfoAndMarks.stitisticInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroomapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class StatisticsInfoFragment extends Fragment {

    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    public StatisticsInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statisticks_info, container, false);
        graphView = rootView.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 9),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 1),
                new DataPoint(8, 2)
        });
        graphView.setTitle("Graph View");
        graphView.setTitleColor(R.color.purple_500);
        graphView.setTitleTextSize(30);
        series.setColor(R.color.purple_700);
        graphView.addSeries(series);
        return rootView;
    }
}