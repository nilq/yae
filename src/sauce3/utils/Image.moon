Texture   = sauce3.java.require "com.badlogic.gdx.graphics.Texture"
Constants = require "sauce3.wrappers"

class
  new: (path, format = "rgba8", f_type) =>
    file = sauce3.File path, f_type

    @texture = sauce3.java.new Texture, file.file, Constants.formats[format], false
    @texture\setFilter Constants.filters["linear"], Constants.filters["linear"]

  get_dimensions: =>
    @get_width!, @get_height!

  get_filter: =>
    min_filter = @texture\getMinFilter!
    mag_filter = @texture\getMagFilter!

    Constants.filter_codes[min_filter], Constants.filter_codes[mag_filter]

  get_format: =>
    texture_data = @texture\getTextureData!
    format       = texture_data\getFormat!

    Constants.format_codes[format]

  get_width: =>
    @texture\getWidth!

  get_height: =>
    @texture\getHeight!

  get_wrap: =>
    Constants.wraps[@texture\getUWrap!], Constants.wraps[@texture\getVWrap!]

  set_filter: (min, mag) =>
    @texture\setFilter Constants.filters[min], Constants.filters[mag]

  set_wrap: (h, v) =>
    @texture\setWrap Constants.wraps[h], Constants.wraps[v]

  dispose: =>
    @texture\dispose!
