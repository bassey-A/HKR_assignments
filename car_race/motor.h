#ifndef MOTOR_H_
#define MOTOR_H_
#include <avr/io.h>
#define FORWARD 0
#define BACKWARD 1
#define STOP 2
#define LEFT_BACKWARD 3
#define RIGHT_BACKWARD 4
void motor_init();
void motor_left_speed(uint8_t);
void motor_right_speed(uint8_t);
void motor_left_direction(uint8_t);
void motor_right_direction(uint8_t);
void motor_speed(uint8_t, uint8_t);
void motor_direction(uint8_t);

#endif /* MOTOR_H_ */