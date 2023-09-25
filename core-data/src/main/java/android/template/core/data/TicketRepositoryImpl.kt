package android.template.core.data

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.template.core.database.model.TicketDBEntity
import android.template.core.database.TicketDao
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class TicketRepositoryImpl(
    private val dao: TicketDao,
    private val context: Context
) : TicketRepository {
    private var databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("note")
    override fun getTickets(): Flow<List<TicketDBEntity>> {
        return dao.getTickets()
    }

    override suspend fun getTicketById(id: String): TicketDBEntity? {
        return dao.getTicketById(id)
    }

    override suspend fun insertTicket(ticket: TicketDBEntity) {
        val newTicket = TicketDBEntity(
            id = UUID.randomUUID().toString(),
            driverName = ticket.driverName,
            inboundWeight = ticket.inboundWeight,
            outboundWeight = ticket.outboundWeight,
            netWeight = ticket.netWeight,
            timestamp = ticket.timestamp,
            licenseNumber = ticket.licenseNumber
        )
        if (ticket.id.isEmpty()) {
            if (isDeviceOnline()) {
                val ticketReference = databaseReference.push()
                ticketReference.setValue(newTicket)
            }
            dao.insertTicket(newTicket)
        } else {
            if (isDeviceOnline()) {
                val updateReference = databaseReference.child(ticket.id)
                updateReference.setValue(ticket)
            }
            dao.insertTicket(ticket)
        }
    }

    override suspend fun deleteTicket(ticket: TicketDBEntity) {
        dao.deleteTicket(ticket)
        if (isDeviceOnline()) {
            val ticketReference = databaseReference.child(ticket.id)
            ticketReference.removeValue()
        }
    }

    @SuppressLint("MissingPermission")
    private fun isDeviceOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}