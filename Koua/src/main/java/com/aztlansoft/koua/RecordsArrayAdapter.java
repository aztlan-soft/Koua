package com.aztlansoft.koua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.aztlansoft.koua.model.KouaMessage;
import java.util.List;

/**
 * Array Adapter
 */
public class RecordsArrayAdapter extends ArrayAdapter<KouaMessage>
{
  private final Context context;
  private final List<KouaMessage> values;
  private int color;

  public RecordsArrayAdapter(Context context, List<KouaMessage> values)
  {
    super(context, R.layout.rowlayout, values);
    this.context = context;
    this.values = values;
    color = context.getResources().getColor(android.R.color.black);
  }

//  @Override
//  public int getViewTypeCount()
//  {
//    return 2;
//  }

//  @Override
//  public int getItemViewType(int position)
//  {
//    return position % 2;
//  }

  public static class ViewHolder
  {
    public TextView t;
    public ImageView i;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    View rowView;

    if (convertView != null)
    {
      rowView = convertView;
    } else
    {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      rowView = inflater.inflate(R.layout.rowlayout, parent, false);

      TextView textView = (TextView) rowView.findViewById(R.id.label);
      ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
      ViewHolder holder = new ViewHolder();
      holder.t = textView;
      holder.i = imageView;
      rowView.setTag(holder);

    }
    ViewHolder tag = (ViewHolder) rowView.getTag();

    tag.t.setText(values.get(position).getOperacion() + "  " + values.get(position).getMonto());

    if (values.get(position).getOperacion().equals("Deposito"))
    {
      tag.i.setImageResource(R.drawable.ic_launcher);
    }
    else
    {
      tag.i.setImageResource(R.drawable.coins);
    }

    rowView.setBackgroundColor(color);
    return rowView;
  }
}
