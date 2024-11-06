import 'dart:io';

main() {
  String data;
  var file = new File('./file.csv');
  file.create();
    try {
      stdout.write('Input Data: ');
      data = stdin.readLineSync()!;
      file.writeAsStringSync(data.toString() + ', ', mode: FileMode.append);
      print('Saved!');
    } catch (e) {
      print('Error!');
    }
}
