package com.adilaytan.medicaldictionarypro.SubRecycler;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adilaytan.medicaldictionarypro.R;

import java.util.List;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.SubHolder>{

    private List<SubtermItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallBack itemclickcallback;

    public interface ItemClickCallBack
    {
       /* void onCharViewClick(int p);
        void onLikeClick(int p);
        void onShareClick(int p);*/
    }

    public void setItemClickCallback(final ItemClickCallBack itemclickcallback)
    {
        this.itemclickcallback = itemclickcallback;
    }

    public RecylerAdapter(List<SubtermItem> list)
    {
       // this.inflater = LayoutInflater.from(c);
        //this.listData = new ArrayList<>();
        this.listData = list;
    }

    public SubHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_term_item,parent,false);
        return new SubHolder(view);
    }

    public void onBindViewHolder(SubHolder holder, int position) {
        SubtermItem subtermItem = listData.get(position);
        holder.name.setText(subtermItem.getName());
        holder.desc.setText(subtermItem.getDescription());

        /*if(charicItem.isLike())
        {
            holder.like.setImageResource(R.drawable.favaorite_heart);
        }
        else
        {
            holder.like.setImageResource(R.drawable.favourite_outline);
        }*/
    }

    public void setListData(List<SubtermItem> list)
    {
     this.listData.clear();
     this.listData.addAll(list);
    }

    public int getItemCount() {
        return listData.size();
    }

    class SubHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private TextView name;
        private TextView desc;
        private View container;

        public SubHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.textviewSub);
            container = itemView.findViewById(R.id.sub_term_item_cont);
            desc = (TextView) itemView.findViewById(R.id.textView_description);

            /*share = (ImageButton) itemView.findViewById(R.id.share_pic);
            like = (ImageButton) itemView.findViewById(R.id.fav_add);

            share.setOnClickListener(this);
            like.setOnClickListener(this);*/
            //container.setOnClickListener(this);
        }

        public void onClick(View view) {
            int id = view.getId();
            /*switch (id)
            {
                case R.id.char_view:

                break;

            }*/

            /*if (view.getId() == R.id.char_view)
            {
                itemclickcallback.onCharViewClick(getAdapterPosition());
            }
            else if (view.getId() == R.id.fav_add)
            {
                itemclickcallback.onLikeClick(getAdapterPosition());
            }
            else if (view.getId() == R.id.share_pic)
            {
                itemclickcallback.onShareClick(getAdapterPosition());
            }*/
        }

    }
}
