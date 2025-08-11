package com.example.treasure_hunt_fixed.model
import kotlin.math.*

object GeofenceUtils {
    private const val EARTH_RADIUS_KM =6371.0

    //function to calculate the distance between two points on Earth's surface using the Haversine formula

    fun calculateDistance(lat1:Double, long1:Double, lat2:Double,long2:Double):Double{
        val dLat = Math.toRadians(lat2-lat1)
        val dLong= Math.toRadians(long2-long1)

        val a = sin(dLat/2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))*
                sin(dLong / 2).pow(2)

        val c = 2*atan2(sqrt(a),sqrt(1-a))

        return EARTH_RADIUS_KM * c
    }
}