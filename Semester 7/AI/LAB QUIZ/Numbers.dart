// DFA (a+b)*(ab+ba)
import 'dart:io';
main() {
  int _number;
  bool isNumber = false;
  var file = new File('./file.txt');
  file.create();
  do {
    try {
      stdout.write('Input Number(s): ');
      _number = int.parse(stdin.readLineSync());
      if (RegExp('[0-9]').hasMatch(_number.toString())) {
        print('Input is numeric');
        file.writeAsStringSync(_number.toString() + '\n',
            mode: FileMode.append);
        print('Input is successfully stored in the file...');
        isNumber = true;
      } else {
        print('Wrong Input! \nPlease Enter Integers Only');
        isNumber = false;
      }
    } catch (e) {
      print('Wrong Input! \nPlease Enter Integers Only');
      isNumber = false;
    }
  } while (!isNumber);
}
