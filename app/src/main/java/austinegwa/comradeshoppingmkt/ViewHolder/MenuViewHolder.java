package austinegwa.comradeshoppingmkt.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import austinegwa.comradeshoppingmkt.Interfaces.ItemClickListener;
import austinegwa.comradeshoppingmkt.R;

/**
 * Created by gwaza on 1/21/2018.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView category_name, category_description;
    public  ImageView category_image;

   private ItemClickListener itemClickListener;


    public MenuViewHolder(View itemView) {
        super(itemView);
        category_name = (TextView) itemView.findViewById(R.id.category_name);
        category_description =(TextView) itemView.findViewById(R.id.category_description);
        category_image = itemView.findViewById(R.id.category_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
       itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}
