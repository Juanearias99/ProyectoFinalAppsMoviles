package com.example.proyectofinalapps.ui.theme.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.proyectofinalapps.R
import com.example.proyectofinalapps.model.Report
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.DefaultViewportTransitionOptions

@Composable
fun Map(
    modifier: Modifier = Modifier,
    centerUserLocation: Boolean = false,
    mapViewportState: MapViewportState,
    reports: List<Report>,
    onNavigateToDetail: (String) -> Unit = {},
    activateClick: Boolean = false,
    onMapClickListener: (Point) -> Unit = {}
) {
    var clickedPoint by remember { mutableStateOf<Point?>(null) }

    val markerResourceId = R.drawable.red_marker
    val marker = rememberIconImage(
        key = markerResourceId,
        painter = painterResource(markerResourceId)
    )

    MapboxMap(
        modifier = modifier,
        mapViewportState = mapViewportState,
        onMapClickListener = {
            if (activateClick) {
                onMapClickListener(it)
                clickedPoint = it
            }
            true
        }
    ) {
        if (centerUserLocation) {
            MapEffect(Unit) { mapView ->
                mapView.location.updateSettings {
                    locationPuck = createDefault2DPuck(withBearing = true)
                    enabled = true
                    puckBearing = PuckBearing.COURSE
                    puckBearingEnabled = true
                }

                mapViewportState.transitionToFollowPuckState(
                    defaultTransitionOptions = DefaultViewportTransitionOptions.Builder()
                        .maxDurationMs(1000)
                        .build()
                )
            }
        }

        // Punto creado por clic manual
        clickedPoint?.let { point ->
            PointAnnotation(
                point = point,
                onClick = {
                    // Puedes hacer algo si se desea al clic
                    true
                }
            ) {
                iconImage = marker
            }
        }


        reports.forEach { report ->
            PointAnnotation(
                point = Point.fromLngLat(report.localizacion.longitud, report.localizacion.latitud),
                onClick = {
                    onNavigateToDetail(report.idReporte)
                    true
                }
            ) {
                iconImage = marker
            }
        }
    }
}
