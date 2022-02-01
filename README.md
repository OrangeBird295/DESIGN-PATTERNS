# SOLID Principles
### S
Single responsibility principle. (....)
--
`
Class 1 Class ควรมีหน้าที่แค่ 1 หน้าที่
`
- A class should only have one responsibility
- This separates different tasks, problems, and concerns into different classes handling

### O
Open closed principle. (....)
--
- Classes should be open for extension
- Classes should be closed for modification

### L 
Liskov substitution principle. (....)
--
`เมื่อเรา inherit class มาก็จะมีทั้ง 1.Class แม่(Base Class)  / 2.Class ลูก(Sub Class)`

**นิยามคือ:** กระบวณการใดๆ ก็ตามของ Class ลูก. แล้วไปเปลี่ยนสิ่งที่เป็นอยู่ของ Class แม่ ค่าจึงผิด

**ตัวอย่างคือ:** Class ผ.ช. มีความสามารถคือ กินเช้า, กินเที่ยง, กินเย็น แต่เมื่อถูก ----Inherit--> แล้วเป็น Class พระจะกินได้เพียงแค่ เช้ากับเที่ยง    

เพราะฉะนั้น ผ.ช. = พระ ดังนั้นเมื่อสั่งให้ ผ.ช. กินข้าวแล้วนับก็จะผิดพลาด
- A base type should able to be substituted by a subtype without altering the correctness of the program

### I
Interface segregation principle
--
`คล้าย [S] Interface 1 Interface ต้องกำหนดความสามารถแค่จุดประสงค์เดียวไม่ใช่หลายจุดประสงค์`

- Onc interface should serve a specific purpose, not several general purposes
- If there are too much in as interface, separate it
- Remember **YAGNI**: You Aren't Going to Need It

### D
Dependency inversion principle 
--
`Main class or Class process ไม่ควรขึ้นต่อ Low-level Class`

**ตัวอย่างคือ:**  .....
- High-level modules should not depend upon low-level ones, both should depend upon abstractions 
- Abstractions should not depend upon details, details should depend upon abstractions
