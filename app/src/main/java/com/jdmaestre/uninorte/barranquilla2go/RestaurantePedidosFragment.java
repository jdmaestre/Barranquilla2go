package com.jdmaestre.uninorte.barranquilla2go;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jose on 05/11/2014.
 */
public class RestaurantePedidosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pedidos_restaurantes, container, false);
        return rootView;
    }
}
