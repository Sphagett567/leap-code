String data; 

const int RED = 13;
const int YELLOW = 12;
const int GREEN = 11;

String readString;

void setup() {
  Serial.begin(9600);
  
  pinMode(RED, OUTPUT); 
  pinMode(YELLOW, OUTPUT); 
  pinMode(GREEN, OUTPUT); 
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
  } else if (data == "red_on") {
    digitalWrite(RED, HIGH);
  } else if (data == "yellow_on") {
    digitalWrite(YELLOW, HIGH);
  } else if (data == "green_on") {
    digitalWrite(GREEN, HIGH);
  } else {
    Serial.print("Unable to parse command: ");
    Serial.print(data);
    Serial.print("\n");
  }
}

void clearPins() {
    digitalWrite(RED, LOW);
    digitalWrite(YELLOW, LOW);
    digitalWrite(GREEN, LOW);
}

