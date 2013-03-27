wlk-reader
==========

Structure and program for reading *.wlk files which store meteorological data for Davis Vantage Pro2 meteorological station.

Usage
-----

<ol>
<li>Create an instance from class WlkReader, where you can specify directory of the *.wlk files, start date and time from where onward to read, end date and time and whether you want unit conversion:</li>
</ol>

    WlkReader wlkReader = new WlkReader(new File("C:/davisvan"), new DateTime(2012, 2, 5, 14, 0), new DateTime(2012, 7, 23, 16, 30), true)

    
Usage2
-----

    require 'github/markup'
    GitHub::Markup.render('README.markdown', "* One\n* Two")

Or, more realistically:

    require 'github/markup'
    GitHub::Markup.render(file, File.read(file))
    