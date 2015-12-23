# config4j
A smart configuration library for Java


### Using

Default config file `config.ini`, content of config file like: 

```code
[section_name]
config_parameter=config_value

[another_section_name]
config_parameter=config_value
```

Get configuration parameter:

```code
Config config = Config.getInstance(configFolderPath, configFilename);
String parameter = config.getParameter(section, parameter);
```
