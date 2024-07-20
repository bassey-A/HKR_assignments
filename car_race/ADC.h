#ifndef ADC_H_INCLUDED
#define ADC_H_INCLUDED
#include <avr/io.h>

#define SENSORS 4

void ADC_init(void);
uint8_t ADC_8bit_single(uint8_t);
void read_sensors(uint8_t*);
#endif