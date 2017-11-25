package vn.magik.atmsach.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.atmsach.R;
import vn.magik.atmsach.activity.resultSearchActivity.IResultSearch;
import vn.magik.atmsach.model.BookTitle;
import vn.magik.atmsach.util.Utils;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class AdapterBookTitle extends RecyclerView.Adapter<AdapterBookTitle.CustomViewHolder> {
    private Context mContext;
    private List<BookTitle> bookTitles;
    private IResultSearch.IView mView;

    public AdapterBookTitle(Context mContext, BookTitle.BookTitleResponse bookTitleResponse, IResultSearch.IView mView) {
        this.mContext = mContext;
        this.bookTitles = bookTitleResponse.getBookTitleList();
        this.mView = mView;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_result, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final BookTitle bookTitle = bookTitles.get(position);

        Picasso.with(mContext).load(bookTitle.getLogo()).into(holder.imageBook);
        holder.textNameBook.setText(bookTitle.getName());
        holder.textAuthor.setText(bookTitle.getAuthor());
        holder.textIsbn.setText(bookTitle.getIsbn());
        holder.textPrice.setText(Utils.formatPrice(bookTitle.getListPrice()));
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo send bookTitle back
                mView.setDataResult(bookTitle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookTitles.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_item_book_result)
        CardView layoutItem;
        @BindView(R.id.image_book_result_book)
        ImageView imageBook;
        @BindView(R.id.image_book_result_name)
        TextView textNameBook;
        @BindView(R.id.image_book_result_author)
        TextView textAuthor;
        @BindView(R.id.image_book_result_isbn)
        TextView textIsbn;
        @BindView(R.id.image_book_result_price)
        TextView textPrice;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
