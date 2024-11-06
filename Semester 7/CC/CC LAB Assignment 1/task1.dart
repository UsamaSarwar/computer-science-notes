import 'dart:io';

main() {
  var file = new File('./file.txt');
  // Create Text File
  file.create();
  // Write operation
  stdout.write('User Input: ');
  String _input = stdin.readLineSync();
  file.writeAsStringSync(_input + '\n', mode: FileMode.append);
  // Append Method
  stdout.write('User Input (Append): ');
  _input = stdin.readLineSync();
  file.writeAsStringSync(_input + '\n', mode: FileMode.append);
  // Read Operation
  stdout.write(file.readAsStringSync());
  // Find Word From File
  stdout.write('Search in File: ');
  _input = stdin.readLineSync();
  if (file.readAsStringSync().contains(_input)) {
    print('Found!');
  } else {
    print('Not Found!');
  }

  List _characters = file.readAsStringSync().codeUnits.map((unit) {
    return String.fromCharCode(unit);
  }).toList();
  stdout.write('Alphabetic Characters: ');
  for (var i in _characters) {
    if (RegExp('[a-z]', caseSensitive: false, multiLine: true).hasMatch(i)) {
      stdout.write(i + ' ');
    }
  }
  stdout.write('\nNumeric Characters: ');
  for (var i in _characters) {
    if (RegExp('[0-9]', multiLine: true).hasMatch(i)) {
      stdout.write(i + ' ');
    }
  }
  stdout.write('\nSpecial Characters: ');
  for (var i in _characters) {
    if (!RegExp('[a-z ' ' A-Z 0-9]').hasMatch(i)) {
      stdout.write(i + ' ');
    }
  }
}
