import geocoder


def getLocation():
    g = geocoder.ip('me')
    return str(g.latlng[0])+"," +str(g.latlng[1])+";"
