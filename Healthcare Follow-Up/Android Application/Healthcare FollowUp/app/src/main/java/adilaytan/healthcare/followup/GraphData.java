package adilaytan.healthcare.followup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Collections;


public class GraphData extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    LineGraphSeries<DataPoint> series;
    int count = 0;

    private String date;
    GraphView graph ;
    JSONEncoder js;
    ArrayList<Integer> datam;

    private int max;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.graph);

        js = new JSONEncoder();
        Intent gelen = getIntent();
        String ecg_Data = gelen.getStringExtra("ecgdata");
        date = gelen.getStringExtra("date");
        datam = new ArrayList();
        datam = js.getECGData(ecg_Data);
        max = Collections.max(datam) + 1;
        graph = (GraphView) findViewById(R.id.graph);
        graphSettings(graph);
    }


    protected void onResume() {
       super.onResume();
       mTimer = new Runnable() {
            public void run() {
                if (count < datam.size())
                {
                    series.appendData(new DataPoint(count, datam.get(count)), false,datam.size());
                    count++;
                    mHandler.postDelayed(this, 50);
                }
                else
                {
                    mHandler.removeCallbacks(mTimer);
                    //Toast.makeText( GraphData.this,"Grafik bitti",Toast.LENGTH_SHORT).show();
                }
            }
        };
        mTimer.run();
    }



    private void graphSettings(GraphView gr){

        gr.getViewport().setMinX(0);
        gr.getViewport().setMaxX(datam.size() + 1);
        gr.getViewport().setXAxisBoundsManual(true);


        gr.getViewport().setMinY(0);
        gr.getViewport().setMaxY(max);
        gr.getViewport().setYAxisBoundsManual(true);


        // first mSeries is a line
        series = new LineGraphSeries<>();
        series.setColor(Color.GREEN);
        series.setTitle("Signal");


        gr.addSeries(series);
        gr.setTitle(date);
    }
}
