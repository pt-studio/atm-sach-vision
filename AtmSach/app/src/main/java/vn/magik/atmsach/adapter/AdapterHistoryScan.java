package vn.magik.atmsach.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.atmsach.R;
import vn.magik.atmsach.activity.resultSearchActivity.IResultSearch;
import vn.magik.atmsach.bean.AppConstants;
import vn.magik.atmsach.model.ScanObject;
import vn.magik.atmsach.util.BitmapTransform;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class AdapterHistoryScan extends RecyclerView.Adapter<AdapterHistoryScan.CustomViewHolder> {
    private Context mContext;
    private List<ScanObject> scanObjects;
    private IResultSearch.IView mView;

    public AdapterHistoryScan(Context mContext, List<ScanObject> scanObjects) {
        this.mContext = mContext;
        this.scanObjects = scanObjects;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_scan, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final ScanObject scanObject = scanObjects.get(position);
        Uri uri;
        if (scanObject.getImageFront()!=null) {
            if ( scanObject.getImageFront().equals("") == false){
                uri = Uri.fromFile(new File(scanObject.getImageFront()));
                Picasso.with(holder.imageBookScan.getContext())
                        .load(uri)
                        .transform(new BitmapTransform(AppConstants.MAX_WIDTH, AppConstants.MAX_HEIGHT))
                        .skipMemoryCache()
                        .centerCrop()
                        .fit()
                        .into(holder.imageBookScan);
            }
        }

        if (scanObject.getNameBook()!=null){
            holder.textTitle.setText(scanObject.getNameBook());
        }
        if(scanObject.getCountImage()!= 0) {
            holder.textCountImage.setText(""+scanObject.getCountImage() + " ảnh");
        }

        holder.layoutItem.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                //Todo start activity main

            }
        });
    }

    @Override
    public int getItemCount() {
        return scanObjects.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_item_history_scan)
        CardView layoutItem;
        @BindView(R.id.image_history_scan)
        ImageView imageBookScan;
        @BindView(R.id.text_history_scan_name)
        TextView textTitle;
        @BindView(R.id.text_history_scan_count_image)
        TextView textCountImage;
        @BindView(R.id.text_history_scan_percent_damage)
        TextView textPercentDamage;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
