package austinegwa.comradeshoppingmkt.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import austinegwa.comradeshoppingmkt.Interfaces.ItemClickListener;
import austinegwa.comradeshoppingmkt.R;

/**
 * Created by gwaza on 1/22/2018.
 */

public class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView details_name, details_description;
    public ImageView details_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DetailsViewHolder(View itemView) {
        super(itemView);
        details_name = itemView.findViewById(R.id.details_name);
        details_description =itemView.findViewById(R.id.details_description);
        details_image = itemView.findViewById(R.id.details_image);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
