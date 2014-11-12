package com.jdmaestre.uninorte.barranquilla2go;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jose on 05/11/2014.
 */
public class RestauranteSucursalesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sucursales_restaurantes, container, false);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        ListView list = (ListView)getView().findViewById(R.id.sucursalesListView);
        ArrayList<DireccionSucursal> sucursales = new ArrayList<DireccionSucursal>();

        sucursales.add(new DireccionSucursal("Buenavista", "Calle 98 # 52 - 10"));
        sucursales.add(new DireccionSucursal("Buenavista", "Calle 98 # 52 - 10"));
        sucursales.add(new DireccionSucursal("Buenavista", "Calle 98 # 52 - 10"));
        sucursales.add(new DireccionSucursal("Buenavista", "Calle 98 # 52 - 10"));
        sucursales.add(new DireccionSucursal("X22222", "Calle 98 # 52 - 10"));

        DireccionSucursalAdapter adapter = new DireccionSucursalAdapter(getActivity(),sucursales);

        list.setAdapter(adapter);

    }



}

class DireccionSucursal{

    private String direccion;
    private String title;

    public DireccionSucursal (String title, String direccion){
        this.title = title;
        this.direccion = direccion;
    }

    public void setTitle(String title){this.title = title;}
    public void setDireccion(String direccion){this.direccion = direccion;}

    public String getTitle(){return title;}
    public String getDireccion() {return direccion;}

}

class DireccionSucursalAdapter extends BaseAdapter{

    private ArrayList<DireccionSucursal> listadoSucursales;
    private LayoutInflater inflater;

    public DireccionSucursalAdapter(Context context, ArrayList<DireccionSucursal> sucursales){

        this.inflater = LayoutInflater.from(context);
        this.listadoSucursales = sucursales;

    }


    @Override
    public int getCount() {
        return listadoSucursales.size();
    }

    @Override
    public Object getItem(int i) {
        return listadoSucursales.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ContenedorView contenedor ;

        if (view == null){

            view = inflater.inflate(R.layout.list_sucursales_item, null);

            contenedor = new ContenedorView();
            contenedor.tite = (TextView) view.findViewById(R.id.sucursalesTitle);
            contenedor.direcccion= (TextView) view.findViewById(R.id.sucursalesDireccion);

             view.setTag(contenedor);

        }else {
            contenedor = (ContenedorView) view.getTag();
        }

            DireccionSucursal sucursales = (DireccionSucursal) getItem(i);
            contenedor.tite.setText(sucursales.getTitle());
            contenedor.direcccion.setText(sucursales.getDireccion());

            return view;



    }

    class ContenedorView{
        TextView tite;
        TextView direcccion;
    }
}
