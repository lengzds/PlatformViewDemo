import 'package:flutter/material.dart';
import 'package:flutter_plugin/flutter_plugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool visible = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'show PlatformView:',
            ),
            Container(
              color: Colors.blue,
              width: 300,
              height: 20,
            ),
            Container(
              color: Colors.white,
              child: SizedBox(
                width: 150,
                height: 150,
                child: PlatformTextWidget(),
              ),
            ),
            Container(
              color: Colors.blue,
              width: 300,
              height: 20,
            ),
          ],
        ),
      ),
    );
  }
}
