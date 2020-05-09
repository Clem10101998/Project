package com.example.Clemproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Donnees> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Donnees item);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Donnees item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Donnees> myDataset, OnItemClickListener listener) {

        this.values = myDataset;
        this.listener = listener;
    }

    /*public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }*/

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Donnees currentDonnees = values.get(position);

        holder.txtHeader.setText(currentDonnees.getCountry());
        holder.txtFooter.setText(currentDonnees.getSlug());
       //holder.txtFooter.setText(currentDonnees.getCountryCode());
        /*holder.txtFooter.setText(currentDonnees.getSlug());
        holder.txtFooter.setText(currentDonnees.getNewConfirmed());
        holder.txtFooter.setText(currentDonnees.getTotalConfirmed());
        holder.txtFooter.setText(currentDonnees.getNewDeaths());
        holder.txtFooter.setText(currentDonnees.getTotalDeaths());
        holder.txtFooter.setText(currentDonnees.getNewRecovered());
        holder.txtFooter.setText(currentDonnees.getTotalRecovered());
        holder.txtFooter.setText(currentDonnees.getDate());*/

        /*holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(currentDonnees);
            }
        });
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
