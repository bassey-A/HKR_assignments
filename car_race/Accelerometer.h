#ifndef ACCELEROMETER_H_
#define ACCELEROMETER_H_
#include <avr/io.h>
void gather_accel_data();
void accelerometer_init();
void calculate_collision_vect();

int16_t accel_upper;
int16_t accel_lower;

#endif /* ACCELEROMETER_H_ */