package uk.co.sullenart.waracle.cakes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cake_item.view.*
import uk.co.sullenart.waracle.CakeEntry
import uk.co.sullenart.waracle.R

class CakesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val entries = mutableListOf<CakeEntry>()

    class CakeViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(entry: CakeEntry) {
            containerView.cake_title.text = entry.title

            Glide.with(containerView)
                .load(entry.image)
                .into(containerView.cake_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CakeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cake_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = entries.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CakeViewHolder).bind(entries[position])
    }

    fun setEntries(incoming: List<CakeEntry>) {
        entries.apply {
            clear()
            addAll(incoming)
        }
        notifyDataSetChanged()
    }
}