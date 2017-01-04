## How to Run Application ##

Any web server can be used. Below instructions pertain only to python.

1. `cd /path/to/root/of/project/`
2. `python -m SimpleHTTPServer 7070  ` # Starts serving resources in `pwd` 
3. Go to `http://localhost:7070/web/` in a web browser

## How to test ##
1. `cd /path/to/root/of/project/`
2. `mvn clean test`

## Assumptions ##
- renamed `require­3.html` to `index.html`
- python is installed on machine

## Issues ##
- Page does not dynamically refresh after json file changes

## References ##

- Stackoverflow

