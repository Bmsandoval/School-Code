// Bryan M. Sandoval
// Project Name

/* Promise of Originality
I promise that this source code file has, in it's entirety, been
written by myself and by no other person or persons. If at any time an
exact copy of this source code is found to be used by another person in
this term, I understand that both myself and the student that submitted
the copy will receive a zero on this assignment.
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Readability
#define STRCMP(a,R,b) (strcmp(a,b) R 0)

// Other defines
#define MEM_SIZE 1000000
//#define ARRAY_START 100
#define LINE_SIZE 1000
#define MAX_SYMBOLS 1000
#define COMMENT ';'
#define INSTR_CNT 30
#define CHAR_MAX 128
#define CHAR_MIN 0
#define STACK_LIM 500000
#define MAXTHREADS 5 // INCLUDING MAIN!!!
#define REG_STORE_SIZE 52
// consts and such
typedef int bool;
//                  0	 1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19  20   21     22     23     24     25   26   27   28   29
enum INSTRUCTIONS { TRP, JMP, JMR, BNZ, BGT, BLT, BRZ, MOV, LDA, STR, LDR, STB, LDB, ADD, ADI, SUB, MUL, DIV, AND, OR, CMP, STR_I, LDR_I, STB_I, LDB_I, RUN, END, BLK, LCK, ULK}; //all instructions covering 0-20
enum REGISTERS { R0, R1, R2, R3, R4, R5, R6, R7, PC, SB, SP, FP, SL};
enum BOOLVALS { FALSE, TRUE };
enum TYPE { _NONE, _INT, _BYT, _ISTR};
const char* my_instr[] = { "TRP", "JMP", "JMR", "BNZ", "BGT", "BLT", "BRZ", "MOV", "LDA", "STR", "LDR", "STB", "LDB", "ADD", "ADI", "SUB", "MUL", "DIV", "AND", "OR", "CMP", "STR_I", "LDR_I", "STB_I", "LDB_I", "RUN", "END", "BLK", "LCK", "ULK"};
const char* rVals[] = { "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "PC", "SB", "SP", "FP", "SL"};
struct OPS {
	int opCode;
	int opOne;
	int opTwo;
} IR;
int regCnt = 13;
int pc = -1;
int instrLbl = 0;
int lblCnt = 0;

/***************************************************
* Name: Assemble()
* Purp: Parse .asm file and convert to a runnable byte file
* Inpt: char* asmFile, char* mem, bool testMode
* Otpt: Parsed file in the form of char* array
***************************************************/
char* Assemble(char* asmFile, char* mem, bool testMode) {
	// ********************* First pass - Tokenize the list ****************************** //
	if(testMode)
		printf("\r\nBeginning assembler pass 1\r\n");
	bool errorFound= FALSE;
	int memReq = 0;
	int lineCnt = 0;
	int arrPossible = _NONE;
	char line[LINE_SIZE];
	char* symbolTblChar[MAX_SYMBOLS];
	int symbolTblNums[MAX_SYMBOLS];
	FILE* fp = NULL;
	char delims[] = {',','\t','\r', '\n', ' '};
	char* chk = NULL;
	char* currLine = NULL;
	fp = fopen(asmFile, "r");							// open input file
	if(fp == NULL) {
		printf("File Error\r\n\tFailed to open file named %s\r\n", asmFile);
		return NULL;
	}
	while(fgets(line, sizeof line, fp) != NULL) { 					// grab next line
		// Used to print line if errorFnd == true
		if(currLine != NULL) {
			free(currLine);
			currLine = NULL;
		}
		currLine = calloc(strlen(line)+1, sizeof(char));
		strcpy(currLine, line);
		lineCnt++;
		chk = (char*)strtok( line, delims );
        if(!chk) {
            printf("Error on line %d\r\n\t%s\tBlank line occured\r\n", lineCnt, currLine);
            errorFound = TRUE;
            continue;
        }
		if(arrPossible){
			if(STRCMP(chk,==,".INT")) {
				if(arrPossible != _INT) {
					printf("Error on line %d\r\n\t%s\tCannot put integer in a char array\r\n", lineCnt, currLine);
					errorFound = TRUE;
					continue;
				}
				symbolTblChar[lblCnt] = NULL;
				symbolTblNums[lblCnt] = memReq;
				memReq +=4;
				lblCnt++;
				continue;
			} else if(STRCMP(chk,==,".BYT")) {
				if(arrPossible != _BYT) {
					printf("Error on line %d\r\n\t%s\tCannot put char in an integer array\r\n", lineCnt, currLine);
					errorFound = TRUE;
					continue;
				}
				symbolTblChar[lblCnt] = NULL;
				symbolTblNums[lblCnt] = memReq;
				memReq += 1;
				lblCnt++;
				continue;
			}
			arrPossible = _NONE; 
		}
		int s;									// Start checking if chk matches any supported instructions
		bool found = FALSE;
		// Look for an instruction
		for(s = 0; s < INSTR_CNT; s++){
			if(STRCMP(chk, ==, my_instr[s]))
				found = TRUE;
		} 
		if(!found) {
			symbolTblChar[lblCnt]= strdup(chk);				// Not an instruction, assume symbol.
			chk = (char*)strtok( NULL, delims );				// grab directive/instruction attached to symbol
			if(chk == NULL) {						// succeeds if nothing follows label
				printf("Error on line %d\r\n\t%s\tMissing/invalid Directive/Instruction\r\n", lineCnt, currLine);
				errorFound = TRUE;
				continue;
			}
			symbolTblNums[lblCnt] = memReq;
			if(STRCMP(chk,==,".INT")) {
				memReq +=4;						// if an int, increment memory by 4 bytes
				lblCnt++;
				arrPossible = _INT;
				continue;
			} else if(STRCMP(chk,==,".BYT")) {
				chk = strtok(NULL, delims);
				if(atoi(chk) > 127 || atoi(chk) < 0) {
					printf("Error on line %d\r\n\t%s\tInvalid char value\r\n", lineCnt, currLine);
					errorFound = TRUE;
				}
				memReq +=1;						// if a char, increment memory by 1 byte
				lblCnt++;
				arrPossible = _BYT;
				continue;
			} else if(STRCMP(chk,==,".ALN")) {
				printf("Error on line %d\r\n\t%s\t.ALN not implemented\r\n", lineCnt, currLine);
				continue;
			} else { 
				lblCnt++;
				instrLbl++;
				if(pc < 0) // If pc has not yet been set to a value, set it.
					pc = memReq; 
			}
		} else { // If instruction was found
			if(pc < 0) // if pc has not yet been set to a value, set it.
				pc = memReq;
		}
		if(testMode)
			printf("%s", currLine);
		// Verify it's an available option
			//Verified as an instruction
		if( STRCMP(chk,==,"END") || STRCMP(chk,==,"BLK")){
			//Do nothing on these ones, won't have operands.
		} else if( STRCMP(chk,==,"JMP") || STRCMP(chk,==,"LCK") || STRCMP(chk,==,"ULK")) {										// label
			chk = (char*)strtok( NULL, delims);
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing label\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}
		} else if( STRCMP(chk,==,"JMR")){															// register
			chk = (char*)strtok( NULL, delims);
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing first operand\r\n", lineCnt, currLine);
				errorFound = TRUE;
			} else if( STRCMP(chk,!=,"R0")  && STRCMP(chk,!=,"R1")  && STRCMP(chk,!=,"R2")
			&& STRCMP(chk,!=,"R3")  && STRCMP(chk,!=,"R4")  && STRCMP(chk,!=,"R5")
			&& STRCMP(chk,!=,"R6")  && STRCMP(chk,!=,"R7")  && STRCMP(chk,!=,"R8")
			&& STRCMP(chk,!=,"SB")  && STRCMP(chk,!=,"SL")  && STRCMP(chk,!=,"FP")
			&& STRCMP(chk,!=,"SP")  && STRCMP(chk,!=,"PC")) {
				printf("Error on line %d\r\n\t%s\tInvalid register for first Operand\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}
		} else if( STRCMP(chk,==,"ADI")){															// register immediate
			chk = (char*)strtok( NULL, delims);
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing first operand\r\n", lineCnt, currLine);
				errorFound = TRUE;
			} else if( STRCMP(chk,!=,"R0")  && STRCMP(chk,!=,"R1")  && STRCMP(chk,!=,"R2")
			&& STRCMP(chk,!=,"R3")  && STRCMP(chk,!=,"R4")  && STRCMP(chk,!=,"R5")
			&& STRCMP(chk,!=,"R6")  && STRCMP(chk,!=,"R7")  && STRCMP(chk,!=,"R8")
			&& STRCMP(chk,!=,"SB")  && STRCMP(chk,!=,"SL")  && STRCMP(chk,!=,"FP")
			&& STRCMP(chk,!=,"SP")  && STRCMP(chk,!=,"PC")) {
				printf("Error on line %d\r\n\t%s\tInvalid register for first Operand\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}								// If accurate register, assume correct for this pass

			chk = (char*)strtok( NULL, delims);
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing immediate value\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}
		}else if( STRCMP(chk,==,"BNZ") || STRCMP(chk,==,"BGT") || STRCMP(chk,==,"BLT")				// register label
		|| STRCMP(chk,==,"BRZ") || STRCMP(chk,==,"LDA") || STRCMP(chk,==,"STR")
		|| STRCMP(chk,==,"LDR") || STRCMP(chk,==,"STB") || STRCMP(chk,==,"LDB") || STRCMP(chk,==,"RUN")) {
			chk = (char*)strtok( NULL, delims);
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing first operand\r\n", lineCnt, currLine);
				errorFound = TRUE;
			} else if( STRCMP(chk,!=,"R0")  && STRCMP(chk,!=,"R1")  && STRCMP(chk,!=,"R2")
			&& STRCMP(chk,!=,"R3")  && STRCMP(chk,!=,"R4")  && STRCMP(chk,!=,"R5")
			&& STRCMP(chk,!=,"R6")  && STRCMP(chk,!=,"R7")  && STRCMP(chk,!=,"R8")
			&& STRCMP(chk,!=,"SB")  && STRCMP(chk,!=,"SL")  && STRCMP(chk,!=,"FP")
			&& STRCMP(chk,!=,"SP")  && STRCMP(chk,!=,"PC")) {
				printf("Error on line %d\r\n\t%s\tInvalid register for first Operand\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}								// If accurate register, assume correct for this pass

			chk = (char*)strtok( NULL, delims);
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing label/register\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}
		} else if( STRCMP(chk,==,"MOV") || STRCMP(chk,==,"ADD")	|| STRCMP(chk,==,"SUB") 			// register register
				|| STRCMP(chk,==,"MUL") || STRCMP(chk,==,"CMP") || STRCMP(chk,==,"DIV")
				|| STRCMP(chk,==,"AND") || STRCMP(chk,==,"OR")) {
			chk = (char*)strtok( NULL, delims);
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing first operand\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}
            		if(STRCMP(chk,!=,"R0")  && STRCMP(chk,!=,"R1")  && STRCMP(chk,!=,"R2")
            		&& STRCMP(chk,!=,"R3")  && STRCMP(chk,!=,"R4")  && STRCMP(chk,!=,"R5")
            		&& STRCMP(chk,!=,"R6")  && STRCMP(chk,!=,"R7")  && STRCMP(chk,!=,"R8")
            		&& STRCMP(chk,!=,"SB")  && STRCMP(chk,!=,"SL")  && STRCMP(chk,!=,"FP")
			&& STRCMP(chk,!=,"SP")  && STRCMP(chk,!=,"PC")) {
				printf("Error on line %d\r\n\t%s\tInvalid register for first operand\r\n", lineCnt, currLine);
                                errorFound = TRUE;
			}
			else{
				chk = (char*)strtok( NULL, delims );
				if(chk == NULL) {
                        	        printf("Error on line %d\r\n\t%s\tMissing second operand\r\n", lineCnt, currLine);
                	                errorFound = TRUE;
	                        }
				if(STRCMP(chk,!=,"R0")  && STRCMP(chk,!=,"R1")  && STRCMP(chk,!=,"R2")
				&& STRCMP(chk,!=,"R3")  && STRCMP(chk,!=,"R4")  && STRCMP(chk,!=,"R5")
				&& STRCMP(chk,!=,"R6")  && STRCMP(chk,!=,"R7")  && STRCMP(chk,!=,"R8")
				&& STRCMP(chk,!=,"SB")  && STRCMP(chk,!=,"SL")  && STRCMP(chk,!=,"FP")
				&& STRCMP(chk,!=,"SP")  && STRCMP(chk,!=,"PC")) {
					printf("Error on line %d\r\n\t%s\tInvalid register for second operand\r\n", lineCnt, currLine);
					errorFound = TRUE;
				}
			}								// if both registers accurate, assume correct this round.
		} else if( STRCMP(chk,==,"TRP") ) {
			chk = (char*)strtok( NULL, delims );
			if(chk == NULL) {
				printf("Error on line %d\r\n\t%s\tMissing trap value\r\n",lineCnt, currLine);
				errorFound = TRUE;
			}
			if(STRCMP(chk,!=,"0") && STRCMP(chk,!=,"1") && STRCMP(chk,!=,"2")
			&& STRCMP(chk,!=,"3") && STRCMP(chk,!=,"4") && STRCMP(chk,!=,"10")
			&& STRCMP(chk,!=,"11")&& STRCMP(chk,!=,"99")) {
				printf("Error on line %d\r\n\t%s\tInvalid trap value\r\n", lineCnt, currLine);
				errorFound = TRUE;
			} else if(STRCMP(chk,==,"0")) {
				memReq += 12;
			} else if(STRCMP(chk,==,"99")) {				// Break point encountered
				printf("Error on line %d\r\n\t%s\tTrap 99 not implemented\r\n", lineCnt, currLine);
				errorFound = TRUE;
			}
		} else {
			printf("Error on line %d\r\n\t%s\tInvalid Instruction\r\n", lineCnt, currLine);
			errorFound = TRUE;
		}
		memReq += 12;
		chk = (char*)strtok( NULL, delims);
		if(chk == NULL)								// See if any lines remaining
			continue;
		else if(chk[0] == COMMENT)
			continue;
		else {
			printf("Error on line %d\r\n\t%s\tInstruction has extra operand\r\n", lineCnt, currLine);
			errorFound = TRUE;
		}
	}								
	int i,j;
	for(i = 0; i < lblCnt; i++) {				// check symbol table for uniqueness
		for(j = 0; j < lblCnt; j++) {
			if(i == j) 							// avoid comparing the same locations
				continue;
			if(symbolTblChar[i] == NULL || symbolTblChar[j] == NULL)
				continue;
			if(STRCMP(symbolTblChar[i],==,symbolTblChar[j])) {		// success if two symbols matched
				printf("Error\r\n\tMatching symbols \r\n\t %d \r\n\t %d \r\n", symbolTblNums[i], symbolTblNums[j]);
				errorFound = TRUE;
			}
		}
	}
	if(errorFound == TRUE)
		return NULL;
	/*if(instrLbl != 0){
		pc += instrLbl*4;
		int t = 0;
		for(t = (lblCnt - instrLbl);t < lblCnt; t++){
			symbolTblNums[t] += (instrLbl*4);
		}
	}*/
	// *************************************************************************************** //
	// *************************************************************************************** //
	// ********************* Second Pass - Build .asm file *********************************** //
	// *************************************************************************************** //
	// *************************************************************************************** //
	if(testMode)
		printf("\r\nBeginning assembler pass 2\r\n");
	fseek(fp, 0, SEEK_SET); // move file pointer back to the beginning
	mem = malloc(sizeof(char)*MEM_SIZE); // create a byte file to hold the memory, as well as the stack and the heap.
	int currMem = 0;
	bool firstRun = TRUE;
	arrPossible = _NONE;
	lineCnt = 0;
	// look for the labels again
	while(fgets(line, sizeof line, fp) != NULL) { 					// grab next line
		chk = strtok(line, delims);
		bool instrFnd = FALSE;
		for(i = 0; i < INSTR_CNT; i++) {
			if(STRCMP(chk, ==, my_instr[i])) { // check if it's an instruction
				instrFnd = TRUE;
				break; // if it is an instruction, break out of for loop.
			}
		}
		char* tmpLbl;
		if(instrFnd == FALSE && (arrPossible == _INT || arrPossible == _BYT)){
			tmpLbl = chk;
                        if(STRCMP(chk,==,".INT")) {                             // check if it is an int
                                chk = strtok(NULL, delims);
				if(testMode)
					printf("M:%d\t\tL:%s\t\tT:Int\t\tV:%d\r\n", currMem, tmpLbl, atoi(chk));
                                *(int*)(mem + currMem) = atoi(chk);
                                currMem+=4;
                                lineCnt++;
                                continue;
                        } else if(STRCMP(chk,==,".BYT")) {              // check if it is a char
                                chk = strtok(NULL, delims);
				if(testMode)
					printf("M:%d\t\tL:%s\t\tT:Byt\t\tV:%d\r\n", currMem, tmpLbl, atoi(chk));
                                mem[currMem] = (char)atoi(chk);
                                currMem+=1;
                                lineCnt++;
                                continue;
                        }
			arrPossible = _NONE;
		}
		if(arrPossible == _NONE /*&& arrPossible != _ISTR*/){
			tmpLbl = chk;
			chk = strtok(NULL, delims); /*******************************************************************/
			if(STRCMP(chk,==,".INT")) {				// check if it is an int
				chk = strtok(NULL, delims);
                                if(testMode)
                                        printf("M:%d\t\tL:%s\t\tT:Int\t\tV:%d\r\n", currMem, tmpLbl, atoi(chk));
				*(int*)(mem + currMem) = atoi(chk);
				currMem+=4;
				lineCnt++;
				arrPossible = _INT;
				continue;
			} else if(STRCMP(chk,==,".BYT")) {		// check if it is a char
				chk = strtok(NULL, delims);
                    if(testMode)
                        printf("M:%d\t\tL:%s\t\tT:Byt\t\tV:%d\r\n", currMem, tmpLbl, atoi(chk));
				mem[currMem] = (char)atoi(chk);
				currMem+=1;
				lineCnt++;
				arrPossible = _BYT;
				continue;
			} else if(firstRun){
				if(instrLbl != 0){
					int t = 0;
					for(t = (lblCnt - instrLbl);t < lblCnt; t++){
						if(testMode)
		                    printf("M:N/A\t\tL:%s\t\tT:Code\t\tV:%d\r\n", *(char**)(symbolTblChar + t), symbolTblNums[t]);
					}
				}
				arrPossible = _ISTR;
				firstRun = FALSE;
				if(!instrFnd) {
					for(i = 0; i < INSTR_CNT; i++) {
						if(STRCMP(chk, ==, my_instr[i])) { // check if it's an instruction
							instrFnd = TRUE;
							break; // if it is an instruction, break out of for loop.
						}
					}
				}
			}
		}
		if(!instrFnd) {
			chk = strtok(NULL, delims);
			for(i = 0; i < INSTR_CNT; i++) {
				if(STRCMP(chk, ==, my_instr[i])) { // check if it's an instruction
					instrFnd = TRUE;
					break; // if it is an instruction, break out of for loop.
				}
			}
		}
		lineCnt++;
		// otherwise it is an instruction, will fall through
		// If I'm here, I should be holding an instruction. (i)
		// replace instructions and registers with their integer versions
		*(int*)(mem+currMem) = i; 							// SAVE THE OpcODE
		if(i == BLK || i == END) {
			// Nothing needed for these, job done!
		} else if(i == JMP || i == LCK || i == ULK) {
			chk = strtok(NULL, delims);
			// this will be followed by a single label
			// Every time a label is found, look for it in the symbol table.
			int t;
			for(t = 0; t < lblCnt; t++) {
				if(*(char**)(symbolTblChar + t) == NULL)
					continue;
				else if(STRCMP(chk,==,*(char**)(symbolTblChar + t)))
					break;
				else if(t == lblCnt) {
					printf("Error\r\n\t%s not found in symbol table\r\n", chk);
					return NULL;
				} else
					continue;
			}
			*(int*)(mem + currMem + 4) = symbolTblNums[t];
		} else if(i == TRP) {
			*(int*)chk = *(int*)strtok(NULL, delims);
			// this will have a single immediate value. already tested for validity
			*(int*)(mem + currMem + 4) = atoi(chk);
		} else {
			chk = strtok(NULL, delims);
			int t = 0;
			for(t = 0; t < regCnt; t++) { 					// SAVE REGISTER IN OPERAND 1
				if(STRCMP(chk,==,rVals[t])) {
					*(int*)(mem + currMem + 4) = t;
					break; // out of for loop
				}
			}
			if(i == JMR) { // this is only a register, job done!
				//currMem+=12;
				//continue;
			} else if(i == ADI) { // this is a register followed by immediate
				*(int*)chk = *(int*)strtok(NULL, delims);
				*(int*)(mem + currMem + 8) = atoi(chk);
			} else if(i == BNZ || i == BGT || i == BLT || i == BRZ || i == LDA || i == STR || i == LDR || i == STB || i == LDB || i == RUN) {
				// these are registers followed by labels. STR, LDR, STB, LDB could be register-register
				chk = strtok(NULL, delims);
				if(i == STR || i == LDR || i == STB || i == LDB) {
					for(t = 0; t < regCnt; t++) {
						if(STRCMP(chk,==,rVals[t])) {								// Save register in opd2
							*(int*)(mem+currMem) = i + 12; 							// FIX THE OPCODE
							*(int*)(mem + currMem + 8) = t;							// SAVE THE REGISTER VAL
							t = -1;
							break; // out of for loop
						}
					// these are register-memory
					} if(t >= 0){
						for(t = 0; t < lblCnt; t++) {
							if(*(char**)(symbolTblChar + t) == NULL)
								continue;
							if(STRCMP(chk,==,*(char**)(symbolTblChar + t))) {
								break;
							} else if(t == lblCnt) {
									printf("Error\n\r\t%s not found in symbol table\r\n", chk);
									return NULL;
							}
						}
						*(int*)(mem+currMem + 8) = symbolTblNums[t];
					}
				} else {
					for(t = 0; t < lblCnt; t++) {
						if(*(char**)(symbolTblChar + t) == NULL)
							continue;
						if(STRCMP(chk,==,*(char**)(symbolTblChar + t))){
							break;
						} else	if(t == lblCnt) {
								printf("Error\n\r\t%s not found in symbol table\r\n", chk);
								return NULL;
						}
					}
					*(int*)(mem+currMem + 8) = symbolTblNums[t];
				}
			} else { // i == MOV || i == ADD || i == SUB || i == MUL || i == DIV || i == AND || i == OR || i == CMP
				// these are register register
				chk = strtok(NULL, delims);
				for(t = 0; t < regCnt; t++) { 					// SAVE REGISTER IN OPERAND 2
					if(STRCMP(chk,==,rVals[t])) {
						*(int*)(mem+currMem+8) = t;
						break; // out of for loop
					}
				}
			}
		}
		currMem+=12;
	}
	if(testMode)
		printf("Ending pass 2\r\n");
	if(errorFound == TRUE)
		return NULL;
	return mem;
}
/***************************************************
* Name: RunVM()
* Purp: Use big switch to run through program, starting from *(mem + pc) until TRP 0 is reached.
* Inpt: char* mem, bool testMode
* Otpt: N/A
***************************************************/
void RunVM(char* mem, bool testMode) {
	if(testMode) {
		printf("\r\nStarting VM\r\n");
		int tCtr = pc;
		printf("PC\t\tOPC\t\tOP1\t\tOP2\r\n");
		while(TRUE){
			if(*(int*)(mem + tCtr) == 0 && *(int*)(mem + tCtr + 4) == 0) {
				printf("PC:%d\t\t%s\t\t%d\t\t%d\r\n", tCtr, my_instr[*(int*)(mem + tCtr)], *(int*)(mem + tCtr + 4), *(int*)(mem + tCtr + 8));
				break;
			}
			printf("PC:%d\t\t%s\t\t%d\t\t%d\r\n", tCtr, my_instr[*(int*)(mem + tCtr)], *(int*)(mem + tCtr + 4), *(int*)(mem + tCtr + 8));
			tCtr += 12;
		}
	}
	//////
	int ThreadStackSize = ((MEM_SIZE)-STACK_LIM)/(MAXTHREADS);
	/////
	int startingPC = pc;
	int tVal = 0;
		    // 0  1  2  3  4  5  6  7  pc  sb                           sp                           fp                           sl
	int rgstr[] = {0, 0, 0, 0, 0, 0, 0, 0, pc, MEM_SIZE-1 - REG_STORE_SIZE, MEM_SIZE-1 - REG_STORE_SIZE, MEM_SIZE-1 - REG_STORE_SIZE, MEM_SIZE-ThreadStackSize-1};
	int running = TRUE;
	int thread[MAXTHREADS];
	int prevThread;
	int currThread;
	for(currThread = 0; currThread < MAXTHREADS; currThread++)
		thread[currThread] = 0;
	currThread = 0;
	int chkThread = 0;
	while(running) {
		IR.opCode = *(int*)(mem + rgstr[PC] );
		IR.opOne  = *(int*)(mem + rgstr[PC] + 4);
		IR.opTwo  = *(int*)(mem + rgstr[PC] + 8);
		int currentLine = ((rgstr[PC] - startingPC)/12) + 1 + lblCnt-instrLbl;
		rgstr[PC] += 12;
		switch(IR.opCode) {
			case JMP://1
				rgstr[PC] = IR.opOne;
				break;
			case JMR://2
				rgstr[PC] = rgstr[IR.opOne];
				break;
			case BNZ://3
				if(rgstr[IR.opOne] != 0) {
					rgstr[PC] = IR.opTwo;
					break;
				}
				break;
			case BGT://4
				if(rgstr[IR.opOne] > 0) {
					rgstr[PC] = IR.opTwo;
					break;
				}
				break;
			case BLT://5
				if(rgstr[IR.opOne] < 0) {
					rgstr[PC] = IR.opTwo;
					break;
				}
				break;
			case BRZ://6
				if(rgstr[IR.opOne] == 0) {
					rgstr[PC] = IR.opTwo;
					break;
				}
				break;
			case MOV://7 -  Move value to one register from another
				rgstr[IR.opOne] = rgstr[IR.opTwo];
				break;
			case LDA://8
				rgstr[IR.opOne] = IR.opTwo;
				break;
			case STR://9
				*(int*)(mem + IR.opTwo) = rgstr[IR.opOne];
				break;
			case LDR://10 - load register with int from label
				rgstr[IR.opOne] = *(int*)(mem + IR.opTwo);
				break;
			case STB://11
				mem[IR.opTwo] = *(char*)(rgstr + IR.opOne);
				break;
			case LDB://12 - Load register with byte from label
				rgstr[IR.opOne] = 0;
				*(char*)(rgstr + IR.opOne) = *(mem + IR.opTwo);
				break;
			case ADD://13
				rgstr[IR.opOne] = rgstr[IR.opOne] + rgstr[IR.opTwo];
				break;
			case ADI://14 - Load immediate value into register
				rgstr[IR.opOne] = rgstr[IR.opOne] + IR.opTwo;
				break;
			case SUB://15
				rgstr[IR.opOne] = rgstr[IR.opOne] - rgstr[IR.opTwo];
				break;
			case MUL://16
				rgstr[IR.opOne] = rgstr[IR.opOne] * rgstr[IR.opTwo];
				break;
			case DIV://17
				rgstr[IR.opOne] = rgstr[IR.opOne] / rgstr[IR.opTwo];
				break;
			case AND://18
				if(rgstr[IR.opOne] == 1 && rgstr[IR.opTwo] == 1) {
					rgstr[IR.opOne] = 1;
				} else {
					rgstr[IR.opOne] = 0;
				}
				break;
			case OR://19
				if(rgstr[IR.opOne] == 1 || rgstr[IR.opTwo] == 1) {
					rgstr[IR.opOne] = 1;
				} else {
					rgstr[IR.opOne] = 0;
				}
				break;
			case CMP://20
				if(rgstr[IR.opOne] == rgstr[IR.opTwo]) {
					rgstr[IR.opOne] = 0;
				} else if(rgstr[IR.opOne] > rgstr[IR.opTwo]) {
					rgstr[IR.opOne] = 1;
				} else /* rgstr[IR.opOne] < rgstr[IR.opTwo]*/ {
					rgstr[IR.opOne] = -1;
				}
				break;
			case STR_I://21
				*(int*)(mem + rgstr[IR.opTwo]) = rgstr[IR.opOne];
				break;
			case LDR_I://22
				rgstr[IR.opOne] = *(int*)(mem + rgstr[IR.opTwo]);
				break;
			case STB_I://23
				*(mem + rgstr[IR.opTwo]) = *(char*)(rgstr + IR.opOne);
				break;
			case LDB_I://24
				tVal = rgstr[IR.opTwo];
				rgstr[IR.opOne] = 0;
				*(char*)(rgstr + IR.opOne) = *(mem + tVal);
				break;
			case RUN://25 ... create a new thread. put the value of this new thread into
                for(chkThread = 1; chkThread < MAXTHREADS; chkThread++){
                    if(thread[chkThread] == FALSE) {
						rgstr[IR.opOne] = chkThread;
                        prevThread = currThread;
                        currThread = chkThread;
                        thread[chkThread] = TRUE;
                        int NextMemLoc = MEM_SIZE -1 - REG_STORE_SIZE - (ThreadStackSize * prevThread);
                        int z;
                        for(z = 0; z < regCnt; z++) { // store all registers into memory of prevThread
                                NextMemLoc -= 4;
                                *(int*)(mem+NextMemLoc) = rgstr[z];
                        }
                        // Update the registers (PC, SB, FP, SP, SL)
                        NextMemLoc = MEM_SIZE -1 - REG_STORE_SIZE - (ThreadStackSize * currThread);
                        rgstr[PC] = IR.opTwo;
                        rgstr[SB] = NextMemLoc - REG_STORE_SIZE;
                        rgstr[FP] = NextMemLoc - REG_STORE_SIZE;
                        rgstr[SP] = NextMemLoc - REG_STORE_SIZE;
                        rgstr[SL] = NextMemLoc - ThreadStackSize;
                        break;
					} else if(chkThread == MAXTHREADS -1)
						chkThread = -1;
                }
                if(chkThread < 1) {
                        printf("\r\nError: Too many threads\r\n");
                        return;
                } else
                	continue;
                break;
            case END://26
                thread[currThread] = FALSE;
                break;
            case BLK://27
                if(currThread > 0)
                        break;
                for(chkThread = 1; chkThread < MAXTHREADS; chkThread++) {
                    if(thread[chkThread] == TRUE) {
                        rgstr[PC] -= 12;
                        break;
                    }
                }
                break;
            case LCK://28
                if(*(int*)(mem + IR.opOne) == FALSE) {
                    *(int*)(mem + IR.opOne) = TRUE;
            	}
                else {
                    rgstr[PC] -= 12;
                }
                break;
            case ULK://29
                *(int*)(mem + IR.opOne) = FALSE;
                break;
            case TRP://0
                switch(IR.opOne) {
                    case(0):
                        // exit program
                        return;
                        break;
                    case(1):
                        // print int to stout
                        printf("%d", rgstr[R3]);
                        break;
                    case(2):;
                        int n;
                        scanf ("%d",&n);
                        rgstr[R3] = n;
                        break;
                    case(3):
                        // print char to stout
                        if(rgstr[R3] == 13)
                            printf("\r\n");
                        else
                            printf("%c",(char)rgstr[R3]);
                        fflush(NULL);
                        break;
                    case(4):
                        rgstr[R3] = (int)getchar();
                        break;
                    default:
                            // should never reach this
                        printf("Error\r\n\tOpcode TRP %d not implemented\r\n", IR.opOne);
                }
				break;
			default:
				printf("Error\r\n\tInvalid opcode at memory location %d\r\n", rgstr[PC]);
		}
		int prevThread = currThread;
		while(MAXTHREADS > 0) { // if more threads than just main, check if context switch is required.
			//prevThread = currThread;
			currThread = (currThread+1)%MAXTHREADS;
			if(currThread == 0 || thread[currThread] > 0) { // If we found main, or a thread that needs work, perform context switch
				if(currThread == prevThread) {
					break;
				}
				int NextMemLoc = MEM_SIZE -1 - REG_STORE_SIZE - (ThreadStackSize * prevThread);
				int z;
				for(z = 0; z < regCnt; z++) { // store all registers into memory of prevThread
					NextMemLoc -= 4;
					*(int*)(mem+NextMemLoc) = rgstr[z];
				}
				NextMemLoc = MEM_SIZE -1 - REG_STORE_SIZE - (ThreadStackSize * currThread);
				for(z = 0; z < regCnt; z++) { // retrieve registers from memory of currThread
					NextMemLoc -= 4;
					rgstr[z] = *(int*)(mem+NextMemLoc);
				}
				break;
			}
		}
	}
}

int main(int argc, char *argv[]) {
	char* byte = NULL;
	if(argc == 1) {
		printf("Error\r\n\tNo arguments found, please add assembly file name when opening\r\n");
	} else {
		if(argc == 2)
			byte = Assemble(argv[1], byte, FALSE);
		else if(argc == 3) {
			if(STRCMP("-t",==,argv[2]))
				byte = Assemble(argv[1], byte, TRUE);
		}
		if(byte == NULL) {
			printf("Whoops, byte was NULL!\r\n");
			return 0;
		}
		else {
			if(argc == 2)
				RunVM(byte, FALSE);
			else if(argc == 3) {
				if(STRCMP("-t",==,argv[2])) {
					RunVM(byte, TRUE);
				}
			}
			if(byte == NULL) {
				free(byte);
				byte = NULL;
			}
		}
		if(byte != NULL)
			free(byte);
	}
	return 0;
}
