String data; 

const int A = 13;
const int B = 12;


String readString;

void setup() {
  Serial.begin(9600);
  
  pinMode(A, OUTPUT); 
  pinMode(B, OUTPUT); 
}

void loop() {
  while (Serial.available()) {
    readString = Serial.readString();

    if (readString.length() > 0) {
      boolean valid = debug(readString);

      if (valid) {
        parseData(readString);
      }
    } 
  }
}

boolean debug(String data) {
  if (data != NULL) {
    Serial.print(data);
    Serial.print("\n");

    return true;
  }

  return false;
}

void parseData(String data) {
  clearPins();
  
  if (data == "clear") {
    clearPins();
  } else if (data == "a_on") {
    digitalWrite(A, HIGH);
  } else if (data == "b_on") {
    digitalWrite(B, HIGH);
  } else {
    Serial.print("Unable to parse command: ");
    Serial.print(data);
    Serial.print("\n");
  }
}

void clearPins() {
    digitalWrite(A, LOW);
    digitalWrite(B, LOW);
}

