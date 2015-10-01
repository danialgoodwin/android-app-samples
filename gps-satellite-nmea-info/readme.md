# gps-satellite-nmea-info #
(Needs more work)

This project demonstrates some sample usage of getting more GPS information than just latitude and longitude. Nothing is formatted well, but it works. Getting a real production-ready version for these APIs will take a bit more coding for the front-end part.

For parsing NMEA sentences, `NmeaUtils.parse()` works well. Its dependencies are `GpsPosition` and `NmeaGpsSentence`. To add support for more sentences, just add another enum to `NmeaGpsSentence` and define how the sentence should be parsed. Nothing else would have to change (as long as it is a GPS-related sentence).



## Further Resources ##
- Thanks to [https://gist.github.com/javisantana/1326141](https://gist.github.com/javisantana/1326141). Some snippets for the parser were used so that I didn't have to look it up myself. Before a production build, I would double-check these values from the next link.
- [http://www.gpsinformation.org/dale/nmea.htm](http://www.gpsinformation.org/dale/nmea.htm)
- [http://developer.android.com/reference/android/location/GpsSatellite.html](http://developer.android.com/reference/android/location/GpsSatellite.html)
- [http://en.wikipedia.org/wiki/NMEA_0183](http://en.wikipedia.org/wiki/NMEA_0183)
- Haven't gone all the way through yet:
  - [http://ktuukkan.github.io/marine-api/](http://ktuukkan.github.io/marine-api/)
  - [https://github.com/HvB/UsbGps4Droid/blob/master/src/org/broeuschmeul/android/gps/nmea/util/NmeaParser.java](https://github.com/HvB/UsbGps4Droid/blob/master/src/org/broeuschmeul/android/gps/nmea/util/NmeaParser.java)
  - [http://www.catb.org/gpsd/NMEA.html](http://www.catb.org/gpsd/NMEA.html)
  - [http://opencpn.org/ocpn/nmea_sentences](http://opencpn.org/ocpn/nmea_sentences)
