package com.example.picovid_19.Fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.picovid_19.Model.GlobalModel;
import com.example.picovid_19.R;
import com.example.picovid_19.viewmodel.GlobalViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class GlobalFragment extends Fragment {

    private ProgressDialog mProgressApp;

    public GlobalFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_global, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Menampilkan tampilan  proses dialog
        super.onViewCreated(view, savedInstanceState);
        mProgressApp = new ProgressDialog(getActivity());
        mProgressApp.setTitle("Mohon tunggu");
        mProgressApp.setCancelable(false);
        mProgressApp.setMessage("Sedang menampilkan data...");
        mProgressApp.show();

        //Mengambil ID Piechart di layout
        PieChart pieChart = view.findViewById(R.id.pieWorld);

        //Memanggil class GlobalViewModel
        GlobalViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(GlobalViewModel.class);
        viewModel.setSummaryGlobal();
        viewModel.getSummaryGlobal().observe(this, new Observer<GlobalModel>() {
            @Override
            public void onChanged(GlobalModel globalModel) {
                //mengabaikan progres dialog
                mProgressApp.dismiss();

                //memasukkan nilai dari globalmodel ke pie
                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(globalModel.getConfirmed().getValue(), getResources().getString(R.string.confirmed)));
                entries.add(new PieEntry(globalModel.getDeaths().getValue(), getResources().getString(R.string.deaths)));
                entries.add(new PieEntry(globalModel.getRecovered().getValue(), getResources().getString(R.string.recovered)));

                //konfigurasi pieChart
                PieDataSet pieDataSet = new PieDataSet(entries, getResources().getString(R.string.corona));
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextColor(Color.WHITE);
                pieDataSet.setValueTextSize(14);

                Description description = new Description();
                description.setText(getResources().getString(R.string.last_update) + " : " + globalModel.getLastUpdate());
                description.setTextColor(Color.BLACK);
                description.setTextSize(12);

                //memasang legend dengan bentuk bulat pada piechart
                Legend legend = pieChart.getLegend();
                legend.setTextColor(Color.BLACK);
                legend.setTextSize(12);
                legend.setForm(Legend.LegendForm.CIRCLE);

                PieData pieData = new PieData(pieDataSet);
                pieChart.setVisibility(View.VISIBLE);
                pieChart.animateXY(2000, 2000);
                pieChart.setDescription(description);
                pieChart.setHoleColor(Color.TRANSPARENT);
                pieChart.setHoleRadius(50);
                pieChart.setRotationAngle(320);
                pieChart.setData(pieData);
            }
        });
    }
}