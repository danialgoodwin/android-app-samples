package net.simplyadvanced.gpsandsatelliteinfo;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


// More info: http://developer.android.com/reference/android/location/GpsSatellite.html
// More info: http://www.gpsinformation.org/dale/nmea.htm
// More info: http://en.wikipedia.org/wiki/NMEA_0183
// NMEA Parser: https://gist.github.com/javisantana/1326141
// NMEA Parser: http://ktuukkan.github.io/marine-api/
// NMEA Parser: https://github.com/ktuukkan/marine-api
// NMEA Parser: http://www.codeproject.com/Articles/279647/NMEA-sentence-parser-builder
// NMEA Parser (one file): https://github.com/HvB/UsbGps4Droid/blob/master/src/org/broeuschmeul/android/gps/nmea/util/NmeaParser.java
/** The main page for this app. */
public class MainActivity extends ActionBarActivity {
    private static final String LOGCAT_TAG = "DEBUG: MainActivity";
    private static void log(String message) {
        Log.d(LOGCAT_TAG, message);
    }

    private TextView mGpsTextView;
    private TextView mNmeaTextView;

    private static GpsStatus mGpsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGpsTextView = (TextView) findViewById(R.id.gpsText);
        mNmeaTextView = (TextView) findViewById(R.id.nmeaText);

        setupGpsListener(this);
    }

    private void setupGpsListener(Context context) {
        final LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0L, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                log("onLocationChanged()");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                log("onStatusChanged(provider=" + provider + ", status=" + status + ")");
            }

            @Override
            public void onProviderEnabled(String provider) {
                log("onProviderEnabled(provider=" + provider + ")");
            }

            @Override
            public void onProviderDisabled(String provider) {
                log("onProviderDisabled(provider=" + provider + ")");
            }
        });
        lm.addGpsStatusListener(new GpsStatus.Listener() {
            @Override
            public void onGpsStatusChanged(int event) {
                mGpsStatus = lm.getGpsStatus(mGpsStatus); // Updates GpsStatus.
                mGpsTextView.setText("Just a test!!!!!!");
                mGpsTextView.setText(extractGpsStatusInfo(mGpsStatus));
                switch (event) {
                    case GpsStatus.GPS_EVENT_FIRST_FIX:
                        log("onGpsStatusChanged(" + event + "), GPS_EVENT_FIRST_FIX");
                        break;
                    case GpsStatus.GPS_EVENT_STARTED:
                        log("onGpsStatusChanged(" + event + "), GPS_EVENT_STARTED");
                        break;
                    case GpsStatus.GPS_EVENT_STOPPED:
                        log("onGpsStatusChanged(" + event + "), GPS_EVENT_STOPPED");
                        break;
                    case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                        log("onGpsStatusChanged(" + event + "), GPS_EVENT_SATELLITE_STATUS");
                        break;
                }
            }
        });
        lm.addNmeaListener(new GpsStatus.NmeaListener() {
            @Override
            public void onNmeaReceived(long timestamp, String nmea) {
                log("onNmeaReceived()");
                mNmeaTextView.setText(extractNmeaInfo(timestamp, nmea));
            }
        });
    }

    private static String extractGpsStatusInfo(GpsStatus gpsStatus) {
        String info = "GpsStatus: ";
        if (gpsStatus == null) { return info + "null"; }

        info += "\nNumber of satellites: " + gpsStatus.getSatellites();
        info += "\nMax satellites: " + gpsStatus.getMaxSatellites();
        info += "\nTime to first fix: " + gpsStatus.getTimeToFirstFix();

        Iterable<GpsSatellite> satellites = gpsStatus.getSatellites();
        for (GpsSatellite satellite : satellites) {
            info += "\nSatellite " + satellite.toString() + ": azimuth=" + satellite.getAzimuth();
            info += "\nSatellite " + satellite.toString() + ": elevation=" + satellite.getElevation();
            info += "\nSatellite " + satellite.toString() + ": prn=" + satellite.getPrn();
            info += "\nSatellite " + satellite.toString() + ": snr=" + satellite.getSnr();
            info += "\nSatellite " + satellite.toString() + ": hasAlmanac=" + satellite.hasAlmanac();
            info += "\nSatellite " + satellite.toString() + ": usedInFix=" + satellite.usedInFix();
            info += "\nSatellite " + satellite.toString() + ": hasEphemeris=" + satellite.hasEphemeris();
        }

        return info;
    }

    private static String extractNmeaInfo(long timestamp, String nmea) {
        String info = "NmeaInfo: ";
        if (nmea == null) { return info + "null"; }
        if (nmea.isEmpty()) { return info + "empty"; }

        info += "\ntimestamp: " + timestamp;

        log("extractNmeaInfo(), nmea=" + nmea);
//        log("extractNmeaInfo(), infos.length=" + infos.length);

        GpsPosition gpsPosition = new GpsPosition();
        boolean isSuccessfulParse = NmeaUtils.parse(nmea, gpsPosition);
        if (isSuccessfulParse) {
            info += "\nGPS fix data: " + NmeaUtils.getType(nmea);
            info += "\n" + gpsPosition.toString();
        } else {
            info += "unsuccessful parse";
        }

        return info;
    }

    private static String getNmeaFixQualityString(String quality) {
        if (quality == null || quality.isEmpty() || !Character.isDigit(quality.charAt(0))) {
            return "Unknown";
        }

        switch (Integer.parseInt(quality)) {
            case 0: return "Invalid";
            case 1: return "GPS fix (SPS)";
            case 2: return "DGPS fix";
            case 3: return "PPS fix";
            case 4: return "Real Time Kinematic";
            case 5: return "Float RTK";
            case 6: return "Estimated (dead reckoning)";
            case 7: return "Manual input mode";
            case 8: return "Simulation mode";
        }
        return "Unknown";
    }

}
