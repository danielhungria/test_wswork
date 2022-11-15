package com.example.carappwswork.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carappwswork.activity.CarDetailActivity
import com.example.carappwswork.core.Constants
import com.example.carappwswork.databinding.ActivityCarCardBinding
import com.example.carappwswork.databinding.HeaderItemBinding
import com.example.carappwswork.extensions.configuraImagemCarro
import com.example.carappwswork.model.AdapterItem
import com.example.carappwswork.model.CarListItem
import com.example.carappwswork.model.HeaderItem

class ListCarAdapter(
    private val context: Context,
    carsList: List<AdapterItem> = emptyList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val adapterList = carsList.toMutableList()

    class CarViewHolder(
        private val binding: ActivityCarCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(carListItem: CarListItem) = with(binding){
            carNomeMarca.text = carListItem.marca_nome
            carNomeModelo.text = carListItem.nome_modelo
            carAno.text = carListItem.ano.toString()
            carCor.text = "Cor: " + carListItem.cor
            carFipe.text = "Fipe: R$" + carListItem.valor_fipe.toString()
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CarDetailActivity::class.java).apply {
                    putExtra(Constants.CAR_ADAPTER_ITEM, carListItem)
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                itemView.context.startActivity(intent)
            }
            imageViewCarCard.configuraImagemCarro(carListItem.nome_modelo)
        }
    }

    class HeaderViewHolder(private val binding: HeaderItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(headerItem: HeaderItem){
            binding.texViewHeader.text = headerItem.titulo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return when(viewType){
            Constants.ADAPTER_ITEM_CAR_TYPE -> CarViewHolder(
                ActivityCarCardBinding.inflate(inflater,parent,false)
            )
            Constants.ADAPTER_ITEM_HEADER_TYPE -> HeaderViewHolder(
                HeaderItemBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalArgumentException("Adapter Item Type Inválido")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is CarViewHolder -> holder.onBind(adapterList[position] as CarListItem)
            is HeaderViewHolder -> holder.onBind(adapterList[position] as HeaderItem)
        }
    }

    override fun getItemViewType(position: Int) = adapterList[position].itemViewType()

    override fun getItemCount(): Int {
        return adapterList.size
    }

}
