// DFA (a+b)*(ab+ba)
import 'dart:io';

main() {
  String _inputString;
  bool isNumber = false;
  var file = new File('./file.txt');
  file.create();
  do {
    stdout.write('Rule: DFA ending at ab or ba\nInput String: ');
    _inputString = stdin.readLineSync();
    if (RegExp('(a+b)*(ab|ba)').hasMatch(_inputString)) {
      file.writeAsStringSync(_inputString.toString() + '\n',
          mode: FileMode.append);
      print('Valid Input is successfully stored in the file...');
      isNumber = true;
    } else {
      print('Invalid String! \nPlease Enter Strings ending at ab or ba');
      isNumber = false;
    }
  } while (!isNumber);
}
