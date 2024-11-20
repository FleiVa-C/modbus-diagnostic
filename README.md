# Modbus Diagnostic
Modbus Diagnostic is a diagnostic tool to quickly troubleshoot modbus reading configurations.

## Description
When configuring a modbus reading configuration and the Modbus Master was not programmed
yourself, the reading values might not always match the expected results. This mostly
happens when multiple registers are used for one value (e.g. Float values that take up
two 4 Byte registers). With the diagnostic cli tool one can check quickly how a register shift,
the HighWordAtLowerAddress setting or a combination of both has an effect on the read value.

## Installation

<b>Requirements:</b>
- JDK 21.0.5
- Kotlin 1.9.24
- Gradle 8.10.2 
```
git clone https://github.com/FleiVa-C/modbus-diagnostic
gradle build
unzip cli/build/distribution/cli.zip
cp cli/build/distribution/cli/bin/cli <destination_path>/modbus-diagnostic
```

## Usage
Invocation of the CLI: `modbus-diagnostic`

<b>Registers</b>\
array of the registers as one string\
low byte of the middle register to be considered as the starting point
```
--registers [127.864, 140.31, 84.763] //required array of 2-3 values 
```

<b>Shift</b>\
shift of the registers in bytes
```
--shift -1 //-1 to 1
```
<b>Flip</b>\
flip to change order of bytes read (HighWordAtLowerAddress setting)
```
--flip true //boolean
```

## Feature Roadmap
- [ ] add `--help` flag
- [ ] improve error messages
