# config4j
A smart configuration library for Java


### Using

Default config file: 

```code
filename: config.ini

[section_name]
config_parameter=config_value
```

Get configuration parameter:

```code
Config config = Config.getInstance(configFolderPath, configFilename);
String parameter = config.getParameter(section, parameter);
```
