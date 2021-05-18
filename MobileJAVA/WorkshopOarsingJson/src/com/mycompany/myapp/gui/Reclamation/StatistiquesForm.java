/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reclamation;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.charts.views.LineChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class StatistiquesForm extends Form{
    ArrayList<Reclamation> data = new ArrayList<>();
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, ArrayList<Reclamation> data) {
    CategorySeries series = new CategorySeries(title);
    data = ServiceReclamation.getInstance().getAllReclamation();
    int pt=0;
    int pc=0;
    int pr=0;
    int pp=0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType_rec().equals("Probleme technique"))
                pt=pt+1;
            if (data.get(i).getType_rec().equals("Probleme de connexion"))
                pc=pc+1;
            if (data.get(i).getType_rec().equals("Probleme Au Niveau De Reservation"))
                pr=pr+1;
            if (data.get(i).getType_rec().equals("Probleme Au Niveau De Payemant"))
                pp=pp+1;
        }
        
        series.add("Probléme Technique", pt);
        series.add("Probleme de connexion", pc);
        series.add("Probleme Au Niveau De Reservation", pr);
        series.add("Probleme Au Niveau De Payemant", pp);
    
    return series;
}

    public StatistiquesForm(Form previous) {
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.rgb(10,163,79),ColorUtil.rgb(247,28,5), ColorUtil.rgb(243,137,29)};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Nombre de réclamation par type", data), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
 
    add(c);
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente
}
}
