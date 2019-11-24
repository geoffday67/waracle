# Waracle test notes

* The data feed had no field called `name` so I used `title` instead.
* Recent Android versions disallow non-HTTPS traffic by default but one of the images used HTTP so I added a `network_security_config.xml` to enable cleartext traffic. I wouldn't do this in production but thought it was easiest for this test.
* I ran out of time to do retry-on-failure, sorry.
