package com.google.appinventor.components.runtime.translate;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class TranslatorToken {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_request_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_request_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_response_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_response_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_token_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_token_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_unsigned_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_unsigned_fieldAccessorTable;

    /* loaded from: classes.dex */
    public interface requestOrBuilder extends MessageOrBuilder {
        String getLanguagecode();

        ByteString getLanguagecodeBytes();

        String getSourcelanguage();

        ByteString getSourcelanguageBytes();

        String getTargetlanguage();

        ByteString getTargetlanguageBytes();

        token getToken();

        tokenOrBuilder getTokenOrBuilder();

        String getTotranslate();

        ByteString getTotranslateBytes();

        long getVersion();

        boolean hasLanguagecode();

        boolean hasSourcelanguage();

        boolean hasTargetlanguage();

        boolean hasToken();

        boolean hasTotranslate();

        boolean hasVersion();
    }

    /* loaded from: classes.dex */
    public interface responseOrBuilder extends MessageOrBuilder {
        long getStatus();

        String getTranslated();

        ByteString getTranslatedBytes();

        long getVersion();

        boolean hasStatus();

        boolean hasTranslated();

        boolean hasVersion();
    }

    /* loaded from: classes.dex */
    public interface tokenOrBuilder extends MessageOrBuilder {
        long getGeneration();

        long getKeyid();

        ByteString getSignature();

        ByteString getUnsigned();

        long getVersion();

        boolean hasGeneration();

        boolean hasKeyid();

        boolean hasSignature();

        boolean hasUnsigned();

        boolean hasVersion();
    }

    /* loaded from: classes.dex */
    public interface unsignedOrBuilder extends MessageOrBuilder {
        long getGeneration();

        String getHuuid();

        ByteString getHuuidBytes();

        long getVersion();

        boolean hasGeneration();

        boolean hasHuuid();

        boolean hasVersion();
    }

    private TranslatorToken() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        registerAllExtensions((ExtensionRegistryLite) registry);
    }

    /* loaded from: classes.dex */
    public static final class unsigned extends GeneratedMessageV3 implements unsignedOrBuilder {
        public static final int GENERATION_FIELD_NUMBER = 3;
        public static final int HUUID_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private long generation_;
        private volatile Object huuid_;
        private byte memoizedIsInitialized;
        private long version_;
        private static final unsigned DEFAULT_INSTANCE = new unsigned();

        @Deprecated
        public static final Parser<unsigned> PARSER = new AbstractParser<unsigned>() { // from class: com.google.appinventor.components.runtime.translate.TranslatorToken.unsigned.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public unsigned m1033parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new unsigned(input, extensionRegistry);
            }
        };

        private unsigned(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private unsigned() {
            this.memoizedIsInitialized = (byte) -1;
            this.huuid_ = "";
            this.version_ = 0L;
            this.generation_ = 0L;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private unsigned(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 10:
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.huuid_ = bs;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.version_ = input.readUInt64();
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.generation_ = input.readUInt64();
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TranslatorToken.internal_static_unsigned_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TranslatorToken.internal_static_unsigned_fieldAccessorTable.ensureFieldAccessorsInitialized(unsigned.class, Builder.class);
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
        public boolean hasHuuid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
        public String getHuuid() {
            Object ref = this.huuid_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.huuid_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
        public ByteString getHuuidBytes() {
            Object ref = this.huuid_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.huuid_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
        public boolean hasGeneration() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
        public long getGeneration() {
            return this.generation_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(output, 1, this.huuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(2, this.version_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeUInt64(3, this.generation_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.huuid_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.version_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeUInt64Size(3, this.generation_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof unsigned)) {
                return super.equals(obj);
            }
            unsigned other = (unsigned) obj;
            boolean result = 1 != 0 && hasHuuid() == other.hasHuuid();
            if (hasHuuid()) {
                result = result && getHuuid().equals(other.getHuuid());
            }
            boolean result2 = result && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result2 = result2 && getVersion() == other.getVersion();
            }
            boolean result3 = result2 && hasGeneration() == other.hasGeneration();
            if (hasGeneration()) {
                result3 = result3 && getGeneration() == other.getGeneration();
            }
            return result3 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasHuuid()) {
                hash = (((hash * 37) + 1) * 53) + getHuuid().hashCode();
            }
            if (hasVersion()) {
                hash = (((hash * 37) + 2) * 53) + Internal.hashLong(getVersion());
            }
            if (hasGeneration()) {
                hash = (((hash * 37) + 3) * 53) + Internal.hashLong(getGeneration());
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static unsigned parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data);
        }

        public static unsigned parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data, extensionRegistry);
        }

        public static unsigned parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data);
        }

        public static unsigned parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data, extensionRegistry);
        }

        public static unsigned parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static unsigned parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static unsigned parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static unsigned parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static unsigned parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static unsigned parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1030newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m1032toBuilder();
        }

        public static Builder newBuilder(unsigned prototype) {
            return DEFAULT_INSTANCE.m1032toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1032toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m1029newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements unsignedOrBuilder {
            private int bitField0_;
            private long generation_;
            private Object huuid_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return TranslatorToken.internal_static_unsigned_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TranslatorToken.internal_static_unsigned_fieldAccessorTable.ensureFieldAccessorsInitialized(unsigned.class, Builder.class);
            }

            private Builder() {
                this.huuid_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.huuid_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = unsigned.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1043clear() {
                super.clear();
                this.huuid_ = "";
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.version_ = 0L;
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.generation_ = 0L;
                this.bitField0_ = i2 & (-5);
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return TranslatorToken.internal_static_unsigned_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public unsigned m1056getDefaultInstanceForType() {
                return unsigned.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public unsigned m1037build() {
                unsigned result = m1039buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public unsigned m1039buildPartial() {
                unsigned result = new unsigned(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.huuid_ = this.huuid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.generation_ = this.generation_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1054clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1067setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1045clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1048clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1069setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1035addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1061mergeFrom(Message other) {
                if (other instanceof unsigned) {
                    return mergeFrom((unsigned) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(unsigned other) {
                if (other == unsigned.getDefaultInstance()) {
                    return this;
                }
                if (other.hasHuuid()) {
                    this.bitField0_ |= 1;
                    this.huuid_ = other.huuid_;
                    onChanged();
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasGeneration()) {
                    setGeneration(other.getGeneration());
                }
                m1065mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1062mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                unsigned parsedMessage = null;
                try {
                    try {
                        parsedMessage = (unsigned) unsigned.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
            public boolean hasHuuid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
            public String getHuuid() {
                Object ref = this.huuid_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.huuid_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
            public ByteString getHuuidBytes() {
                Object ref = this.huuid_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.huuid_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setHuuid(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.huuid_ = value;
                onChanged();
                return this;
            }

            public Builder clearHuuid() {
                this.bitField0_ &= -2;
                this.huuid_ = unsigned.getDefaultInstance().getHuuid();
                onChanged();
                return this;
            }

            public Builder setHuuidBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.huuid_ = value;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
            public boolean hasGeneration() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.unsignedOrBuilder
            public long getGeneration() {
                return this.generation_;
            }

            public Builder setGeneration(long value) {
                this.bitField0_ |= 4;
                this.generation_ = value;
                onChanged();
                return this;
            }

            public Builder clearGeneration() {
                this.bitField0_ &= -5;
                this.generation_ = 0L;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1071setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1065mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static unsigned getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<unsigned> parser() {
            return PARSER;
        }

        public Parser<unsigned> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public unsigned m1027getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class token extends GeneratedMessageV3 implements tokenOrBuilder {
        public static final int GENERATION_FIELD_NUMBER = 3;
        public static final int KEYID_FIELD_NUMBER = 2;
        public static final int SIGNATURE_FIELD_NUMBER = 5;
        public static final int UNSIGNED_FIELD_NUMBER = 4;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private long generation_;
        private long keyid_;
        private byte memoizedIsInitialized;
        private ByteString signature_;
        private ByteString unsigned_;
        private long version_;
        private static final token DEFAULT_INSTANCE = new token();

        @Deprecated
        public static final Parser<token> PARSER = new AbstractParser<token>() { // from class: com.google.appinventor.components.runtime.translate.TranslatorToken.token.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public token m982parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new token(input, extensionRegistry);
            }
        };

        private token(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private token() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = 1L;
            this.keyid_ = 1L;
            this.generation_ = 0L;
            this.unsigned_ = ByteString.EMPTY;
            this.signature_ = ByteString.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private token(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.version_ = input.readUInt64();
                            case 16:
                                this.bitField0_ |= 2;
                                this.keyid_ = input.readUInt64();
                            case 24:
                                this.bitField0_ |= 4;
                                this.generation_ = input.readUInt64();
                            case 34:
                                this.bitField0_ |= 8;
                                this.unsigned_ = input.readBytes();
                            case 42:
                                this.bitField0_ |= 16;
                                this.signature_ = input.readBytes();
                            default:
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TranslatorToken.internal_static_token_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TranslatorToken.internal_static_token_fieldAccessorTable.ensureFieldAccessorsInitialized(token.class, Builder.class);
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public boolean hasKeyid() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public long getKeyid() {
            return this.keyid_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public boolean hasGeneration() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public long getGeneration() {
            return this.generation_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public boolean hasUnsigned() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public ByteString getUnsigned() {
            return this.unsigned_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public boolean hasSignature() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt64(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(2, this.keyid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeUInt64(3, this.generation_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, this.unsigned_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, this.signature_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt64Size(1, this.version_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.keyid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeUInt64Size(3, this.generation_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, this.unsigned_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, this.signature_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof token)) {
                return super.equals(obj);
            }
            token other = (token) obj;
            boolean result = 1 != 0 && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result = result && getVersion() == other.getVersion();
            }
            boolean result2 = result && hasKeyid() == other.hasKeyid();
            if (hasKeyid()) {
                result2 = result2 && getKeyid() == other.getKeyid();
            }
            boolean result3 = result2 && hasGeneration() == other.hasGeneration();
            if (hasGeneration()) {
                result3 = result3 && getGeneration() == other.getGeneration();
            }
            boolean result4 = result3 && hasUnsigned() == other.hasUnsigned();
            if (hasUnsigned()) {
                result4 = result4 && getUnsigned().equals(other.getUnsigned());
            }
            boolean result5 = result4 && hasSignature() == other.hasSignature();
            if (hasSignature()) {
                result5 = result5 && getSignature().equals(other.getSignature());
            }
            return result5 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasVersion()) {
                hash = (((hash * 37) + 1) * 53) + Internal.hashLong(getVersion());
            }
            if (hasKeyid()) {
                hash = (((hash * 37) + 2) * 53) + Internal.hashLong(getKeyid());
            }
            if (hasGeneration()) {
                hash = (((hash * 37) + 3) * 53) + Internal.hashLong(getGeneration());
            }
            if (hasUnsigned()) {
                hash = (((hash * 37) + 4) * 53) + getUnsigned().hashCode();
            }
            if (hasSignature()) {
                hash = (((hash * 37) + 5) * 53) + getSignature().hashCode();
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static token parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data);
        }

        public static token parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data, extensionRegistry);
        }

        public static token parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data);
        }

        public static token parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data, extensionRegistry);
        }

        public static token parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static token parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static token parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static token parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static token parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static token parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m979newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m981toBuilder();
        }

        public static Builder newBuilder(token prototype) {
            return DEFAULT_INSTANCE.m981toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m981toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m978newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements tokenOrBuilder {
            private int bitField0_;
            private long generation_;
            private long keyid_;
            private ByteString signature_;
            private ByteString unsigned_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return TranslatorToken.internal_static_token_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TranslatorToken.internal_static_token_fieldAccessorTable.ensureFieldAccessorsInitialized(token.class, Builder.class);
            }

            private Builder() {
                this.version_ = 1L;
                this.keyid_ = 1L;
                this.unsigned_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.version_ = 1L;
                this.keyid_ = 1L;
                this.unsigned_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = token.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m992clear() {
                super.clear();
                this.version_ = 1L;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.keyid_ = 1L;
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.generation_ = 0L;
                this.bitField0_ = i2 & (-5);
                this.unsigned_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                this.signature_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return TranslatorToken.internal_static_token_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public token m1005getDefaultInstanceForType() {
                return token.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public token m986build() {
                token result = m988buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public token m988buildPartial() {
                token result = new token(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.keyid_ = this.keyid_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.generation_ = this.generation_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.unsigned_ = this.unsigned_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.signature_ = this.signature_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1003clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1016setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m994clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m997clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1018setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m984addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1010mergeFrom(Message other) {
                if (other instanceof token) {
                    return mergeFrom((token) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(token other) {
                if (other == token.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasKeyid()) {
                    setKeyid(other.getKeyid());
                }
                if (other.hasGeneration()) {
                    setGeneration(other.getGeneration());
                }
                if (other.hasUnsigned()) {
                    setUnsigned(other.getUnsigned());
                }
                if (other.hasSignature()) {
                    setSignature(other.getSignature());
                }
                m1014mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m1011mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                token parsedMessage = null;
                try {
                    try {
                        parsedMessage = (token) token.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 1;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -2;
                this.version_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public boolean hasKeyid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public long getKeyid() {
                return this.keyid_;
            }

            public Builder setKeyid(long value) {
                this.bitField0_ |= 2;
                this.keyid_ = value;
                onChanged();
                return this;
            }

            public Builder clearKeyid() {
                this.bitField0_ &= -3;
                this.keyid_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public boolean hasGeneration() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public long getGeneration() {
                return this.generation_;
            }

            public Builder setGeneration(long value) {
                this.bitField0_ |= 4;
                this.generation_ = value;
                onChanged();
                return this;
            }

            public Builder clearGeneration() {
                this.bitField0_ &= -5;
                this.generation_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public boolean hasUnsigned() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public ByteString getUnsigned() {
                return this.unsigned_;
            }

            public Builder setUnsigned(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.unsigned_ = value;
                onChanged();
                return this;
            }

            public Builder clearUnsigned() {
                this.bitField0_ &= -9;
                this.unsigned_ = token.getDefaultInstance().getUnsigned();
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public boolean hasSignature() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.tokenOrBuilder
            public ByteString getSignature() {
                return this.signature_;
            }

            public Builder setSignature(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.signature_ = value;
                onChanged();
                return this;
            }

            public Builder clearSignature() {
                this.bitField0_ &= -17;
                this.signature_ = token.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1020setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m1014mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static token getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<token> parser() {
            return PARSER;
        }

        public Parser<token> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public token m976getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class request extends GeneratedMessageV3 implements requestOrBuilder {
        public static final int LANGUAGECODE_FIELD_NUMBER = 4;
        public static final int SOURCELANGUAGE_FIELD_NUMBER = 5;
        public static final int TARGETLANGUAGE_FIELD_NUMBER = 6;
        public static final int TOKEN_FIELD_NUMBER = 2;
        public static final int TOTRANSLATE_FIELD_NUMBER = 3;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private volatile Object languagecode_;
        private byte memoizedIsInitialized;
        private volatile Object sourcelanguage_;
        private volatile Object targetlanguage_;
        private token token_;
        private volatile Object totranslate_;
        private long version_;
        private static final request DEFAULT_INSTANCE = new request();

        @Deprecated
        public static final Parser<request> PARSER = new AbstractParser<request>() { // from class: com.google.appinventor.components.runtime.translate.TranslatorToken.request.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public request m879parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new request(input, extensionRegistry);
            }
        };

        private request(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private request() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = 1L;
            this.totranslate_ = "";
            this.languagecode_ = "";
            this.sourcelanguage_ = "";
            this.targetlanguage_ = "";
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private request(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.version_ = input.readUInt64();
                            case 18:
                                token.Builder subBuilder = (this.bitField0_ & 2) == 2 ? this.token_.m981toBuilder() : null;
                                token tokenVar = (token) input.readMessage(token.PARSER, extensionRegistry);
                                this.token_ = tokenVar;
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(tokenVar);
                                    this.token_ = subBuilder.m988buildPartial();
                                }
                                this.bitField0_ |= 2;
                            case 26:
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 4;
                                this.totranslate_ = bs;
                            case 34:
                                ByteString bs2 = input.readBytes();
                                this.bitField0_ |= 8;
                                this.languagecode_ = bs2;
                            case 42:
                                ByteString bs3 = input.readBytes();
                                this.bitField0_ |= 16;
                                this.sourcelanguage_ = bs3;
                            case 50:
                                ByteString bs4 = input.readBytes();
                                this.bitField0_ |= 32;
                                this.targetlanguage_ = bs4;
                            default:
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TranslatorToken.internal_static_request_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TranslatorToken.internal_static_request_fieldAccessorTable.ensureFieldAccessorsInitialized(request.class, Builder.class);
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public boolean hasToken() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public token getToken() {
            token tokenVar = this.token_;
            return tokenVar == null ? token.getDefaultInstance() : tokenVar;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public tokenOrBuilder getTokenOrBuilder() {
            token tokenVar = this.token_;
            return tokenVar == null ? token.getDefaultInstance() : tokenVar;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public boolean hasTotranslate() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public String getTotranslate() {
            Object ref = this.totranslate_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.totranslate_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public ByteString getTotranslateBytes() {
            Object ref = this.totranslate_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.totranslate_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public boolean hasLanguagecode() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public String getLanguagecode() {
            Object ref = this.languagecode_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.languagecode_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public ByteString getLanguagecodeBytes() {
            Object ref = this.languagecode_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.languagecode_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public boolean hasSourcelanguage() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public String getSourcelanguage() {
            Object ref = this.sourcelanguage_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sourcelanguage_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public ByteString getSourcelanguageBytes() {
            Object ref = this.sourcelanguage_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sourcelanguage_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public boolean hasTargetlanguage() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public String getTargetlanguage() {
            Object ref = this.targetlanguage_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.targetlanguage_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
        public ByteString getTargetlanguageBytes() {
            Object ref = this.targetlanguage_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.targetlanguage_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt64(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getToken());
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(output, 3, this.totranslate_);
            }
            if ((this.bitField0_ & 8) == 8) {
                GeneratedMessageV3.writeString(output, 4, this.languagecode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                GeneratedMessageV3.writeString(output, 5, this.sourcelanguage_);
            }
            if ((this.bitField0_ & 32) == 32) {
                GeneratedMessageV3.writeString(output, 6, this.targetlanguage_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt64Size(1, this.version_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getToken());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += GeneratedMessageV3.computeStringSize(3, this.totranslate_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += GeneratedMessageV3.computeStringSize(4, this.languagecode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += GeneratedMessageV3.computeStringSize(5, this.sourcelanguage_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += GeneratedMessageV3.computeStringSize(6, this.targetlanguage_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof request)) {
                return super.equals(obj);
            }
            request other = (request) obj;
            boolean result = 1 != 0 && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result = result && getVersion() == other.getVersion();
            }
            boolean result2 = result && hasToken() == other.hasToken();
            if (hasToken()) {
                result2 = result2 && getToken().equals(other.getToken());
            }
            boolean result3 = result2 && hasTotranslate() == other.hasTotranslate();
            if (hasTotranslate()) {
                result3 = result3 && getTotranslate().equals(other.getTotranslate());
            }
            boolean result4 = result3 && hasLanguagecode() == other.hasLanguagecode();
            if (hasLanguagecode()) {
                result4 = result4 && getLanguagecode().equals(other.getLanguagecode());
            }
            boolean result5 = result4 && hasSourcelanguage() == other.hasSourcelanguage();
            if (hasSourcelanguage()) {
                result5 = result5 && getSourcelanguage().equals(other.getSourcelanguage());
            }
            boolean result6 = result5 && hasTargetlanguage() == other.hasTargetlanguage();
            if (hasTargetlanguage()) {
                result6 = result6 && getTargetlanguage().equals(other.getTargetlanguage());
            }
            return result6 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasVersion()) {
                hash = (((hash * 37) + 1) * 53) + Internal.hashLong(getVersion());
            }
            if (hasToken()) {
                hash = (((hash * 37) + 2) * 53) + getToken().hashCode();
            }
            if (hasTotranslate()) {
                hash = (((hash * 37) + 3) * 53) + getTotranslate().hashCode();
            }
            if (hasLanguagecode()) {
                hash = (((hash * 37) + 4) * 53) + getLanguagecode().hashCode();
            }
            if (hasSourcelanguage()) {
                hash = (((hash * 37) + 5) * 53) + getSourcelanguage().hashCode();
            }
            if (hasTargetlanguage()) {
                hash = (((hash * 37) + 6) * 53) + getTargetlanguage().hashCode();
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static request parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data);
        }

        public static request parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data, extensionRegistry);
        }

        public static request parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data);
        }

        public static request parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data, extensionRegistry);
        }

        public static request parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static request parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static request parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static request parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static request parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static request parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m876newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m878toBuilder();
        }

        public static Builder newBuilder(request prototype) {
            return DEFAULT_INSTANCE.m878toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m878toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m875newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements requestOrBuilder {
            private int bitField0_;
            private Object languagecode_;
            private Object sourcelanguage_;
            private Object targetlanguage_;
            private SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> tokenBuilder_;
            private token token_;
            private Object totranslate_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return TranslatorToken.internal_static_request_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TranslatorToken.internal_static_request_fieldAccessorTable.ensureFieldAccessorsInitialized(request.class, Builder.class);
            }

            private Builder() {
                this.version_ = 1L;
                this.token_ = null;
                this.totranslate_ = "";
                this.languagecode_ = "";
                this.sourcelanguage_ = "";
                this.targetlanguage_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.version_ = 1L;
                this.token_ = null;
                this.totranslate_ = "";
                this.languagecode_ = "";
                this.sourcelanguage_ = "";
                this.targetlanguage_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (request.alwaysUseFieldBuilders) {
                    getTokenFieldBuilder();
                }
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m889clear() {
                super.clear();
                this.version_ = 1L;
                this.bitField0_ &= -2;
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.token_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                int i = this.bitField0_ & (-3);
                this.bitField0_ = i;
                this.totranslate_ = "";
                int i2 = i & (-5);
                this.bitField0_ = i2;
                this.languagecode_ = "";
                int i3 = i2 & (-9);
                this.bitField0_ = i3;
                this.sourcelanguage_ = "";
                int i4 = i3 & (-17);
                this.bitField0_ = i4;
                this.targetlanguage_ = "";
                this.bitField0_ = i4 & (-33);
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return TranslatorToken.internal_static_request_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public request m902getDefaultInstanceForType() {
                return request.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public request m883build() {
                request result = m885buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public request m885buildPartial() {
                request result = new request(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    result.token_ = this.token_;
                } else {
                    result.token_ = singleFieldBuilderV3.build();
                }
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.totranslate_ = this.totranslate_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.languagecode_ = this.languagecode_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.sourcelanguage_ = this.sourcelanguage_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.targetlanguage_ = this.targetlanguage_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m900clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m913setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m891clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m894clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m915setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m881addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m907mergeFrom(Message other) {
                if (other instanceof request) {
                    return mergeFrom((request) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(request other) {
                if (other == request.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasToken()) {
                    mergeToken(other.getToken());
                }
                if (other.hasTotranslate()) {
                    this.bitField0_ |= 4;
                    this.totranslate_ = other.totranslate_;
                    onChanged();
                }
                if (other.hasLanguagecode()) {
                    this.bitField0_ |= 8;
                    this.languagecode_ = other.languagecode_;
                    onChanged();
                }
                if (other.hasSourcelanguage()) {
                    this.bitField0_ |= 16;
                    this.sourcelanguage_ = other.sourcelanguage_;
                    onChanged();
                }
                if (other.hasTargetlanguage()) {
                    this.bitField0_ |= 32;
                    this.targetlanguage_ = other.targetlanguage_;
                    onChanged();
                }
                m911mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m908mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                request parsedMessage = null;
                try {
                    try {
                        parsedMessage = (request) request.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 1;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -2;
                this.version_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public boolean hasToken() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public token getToken() {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    token tokenVar = this.token_;
                    return tokenVar == null ? token.getDefaultInstance() : tokenVar;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setToken(token value) {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.token_ = value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setToken(token.Builder builderForValue) {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.token_ = builderForValue.m986build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builderForValue.m986build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeToken(token value) {
                token tokenVar;
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (tokenVar = this.token_) != null && tokenVar != token.getDefaultInstance()) {
                        this.token_ = token.newBuilder(this.token_).mergeFrom(value).m988buildPartial();
                    } else {
                        this.token_ = value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearToken() {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.token_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public token.Builder getTokenBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getTokenFieldBuilder().getBuilder();
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public tokenOrBuilder getTokenOrBuilder() {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (tokenOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                token tokenVar = this.token_;
                if (tokenVar != null) {
                    return tokenVar;
                }
                return token.getDefaultInstance();
            }

            private SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> getTokenFieldBuilder() {
                if (this.tokenBuilder_ == null) {
                    this.tokenBuilder_ = new SingleFieldBuilderV3<>(getToken(), getParentForChildren(), isClean());
                    this.token_ = null;
                }
                return this.tokenBuilder_;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public boolean hasTotranslate() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public String getTotranslate() {
                Object ref = this.totranslate_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.totranslate_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public ByteString getTotranslateBytes() {
                Object ref = this.totranslate_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.totranslate_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTotranslate(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.totranslate_ = value;
                onChanged();
                return this;
            }

            public Builder clearTotranslate() {
                this.bitField0_ &= -5;
                this.totranslate_ = request.getDefaultInstance().getTotranslate();
                onChanged();
                return this;
            }

            public Builder setTotranslateBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.totranslate_ = value;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public boolean hasLanguagecode() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public String getLanguagecode() {
                Object ref = this.languagecode_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.languagecode_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public ByteString getLanguagecodeBytes() {
                Object ref = this.languagecode_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.languagecode_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setLanguagecode(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.languagecode_ = value;
                onChanged();
                return this;
            }

            public Builder clearLanguagecode() {
                this.bitField0_ &= -9;
                this.languagecode_ = request.getDefaultInstance().getLanguagecode();
                onChanged();
                return this;
            }

            public Builder setLanguagecodeBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.languagecode_ = value;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public boolean hasSourcelanguage() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public String getSourcelanguage() {
                Object ref = this.sourcelanguage_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sourcelanguage_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public ByteString getSourcelanguageBytes() {
                Object ref = this.sourcelanguage_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sourcelanguage_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSourcelanguage(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.sourcelanguage_ = value;
                onChanged();
                return this;
            }

            public Builder clearSourcelanguage() {
                this.bitField0_ &= -17;
                this.sourcelanguage_ = request.getDefaultInstance().getSourcelanguage();
                onChanged();
                return this;
            }

            public Builder setSourcelanguageBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.sourcelanguage_ = value;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public boolean hasTargetlanguage() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public String getTargetlanguage() {
                Object ref = this.targetlanguage_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.targetlanguage_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.requestOrBuilder
            public ByteString getTargetlanguageBytes() {
                Object ref = this.targetlanguage_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.targetlanguage_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTargetlanguage(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.targetlanguage_ = value;
                onChanged();
                return this;
            }

            public Builder clearTargetlanguage() {
                this.bitField0_ &= -33;
                this.targetlanguage_ = request.getDefaultInstance().getTargetlanguage();
                onChanged();
                return this;
            }

            public Builder setTargetlanguageBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.targetlanguage_ = value;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m917setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m911mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static request getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<request> parser() {
            return PARSER;
        }

        public Parser<request> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public request m873getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class response extends GeneratedMessageV3 implements responseOrBuilder {
        private static final response DEFAULT_INSTANCE = new response();

        @Deprecated
        public static final Parser<response> PARSER = new AbstractParser<response>() { // from class: com.google.appinventor.components.runtime.translate.TranslatorToken.response.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public response m930parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new response(input, extensionRegistry);
            }
        };
        public static final int STATUS_FIELD_NUMBER = 2;
        public static final int TRANSLATED_FIELD_NUMBER = 3;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private long status_;
        private volatile Object translated_;
        private long version_;

        private response(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private response() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = 1L;
            this.status_ = 0L;
            this.translated_ = "";
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private response(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.version_ = input.readUInt64();
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.status_ = input.readUInt64();
                                case 26:
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 4;
                                    this.translated_ = bs;
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TranslatorToken.internal_static_response_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TranslatorToken.internal_static_response_fieldAccessorTable.ensureFieldAccessorsInitialized(response.class, Builder.class);
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
        public long getStatus() {
            return this.status_;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
        public boolean hasTranslated() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
        public String getTranslated() {
            Object ref = this.translated_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.translated_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
        public ByteString getTranslatedBytes() {
            Object ref = this.translated_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.translated_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt64(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(2, this.status_);
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(output, 3, this.translated_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt64Size(1, this.version_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.status_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += GeneratedMessageV3.computeStringSize(3, this.translated_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof response)) {
                return super.equals(obj);
            }
            response other = (response) obj;
            boolean result = 1 != 0 && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result = result && getVersion() == other.getVersion();
            }
            boolean result2 = result && hasStatus() == other.hasStatus();
            if (hasStatus()) {
                result2 = result2 && getStatus() == other.getStatus();
            }
            boolean result3 = result2 && hasTranslated() == other.hasTranslated();
            if (hasTranslated()) {
                result3 = result3 && getTranslated().equals(other.getTranslated());
            }
            return result3 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasVersion()) {
                hash = (((hash * 37) + 1) * 53) + Internal.hashLong(getVersion());
            }
            if (hasStatus()) {
                hash = (((hash * 37) + 2) * 53) + Internal.hashLong(getStatus());
            }
            if (hasTranslated()) {
                hash = (((hash * 37) + 3) * 53) + getTranslated().hashCode();
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static response parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data);
        }

        public static response parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data, extensionRegistry);
        }

        public static response parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data);
        }

        public static response parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data, extensionRegistry);
        }

        public static response parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static response parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static response parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static response parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static response parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static response parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m927newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m929toBuilder();
        }

        public static Builder newBuilder(response prototype) {
            return DEFAULT_INSTANCE.m929toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m929toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m926newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements responseOrBuilder {
            private int bitField0_;
            private long status_;
            private Object translated_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return TranslatorToken.internal_static_response_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TranslatorToken.internal_static_response_fieldAccessorTable.ensureFieldAccessorsInitialized(response.class, Builder.class);
            }

            private Builder() {
                this.version_ = 1L;
                this.translated_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.version_ = 1L;
                this.translated_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = response.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m940clear() {
                super.clear();
                this.version_ = 1L;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.status_ = 0L;
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.translated_ = "";
                this.bitField0_ = i2 & (-5);
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return TranslatorToken.internal_static_response_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public response m953getDefaultInstanceForType() {
                return response.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public response m934build() {
                response result = m936buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public response m936buildPartial() {
                response result = new response(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.status_ = this.status_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.translated_ = this.translated_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m951clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m964setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m942clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m945clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m966setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m932addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m958mergeFrom(Message other) {
                if (other instanceof response) {
                    return mergeFrom((response) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(response other) {
                if (other == response.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasStatus()) {
                    setStatus(other.getStatus());
                }
                if (other.hasTranslated()) {
                    this.bitField0_ |= 4;
                    this.translated_ = other.translated_;
                    onChanged();
                }
                m962mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m959mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                response parsedMessage = null;
                try {
                    try {
                        parsedMessage = (response) response.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 1;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -2;
                this.version_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
            public long getStatus() {
                return this.status_;
            }

            public Builder setStatus(long value) {
                this.bitField0_ |= 2;
                this.status_ = value;
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.bitField0_ &= -3;
                this.status_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
            public boolean hasTranslated() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
            public String getTranslated() {
                Object ref = this.translated_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.translated_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.translate.TranslatorToken.responseOrBuilder
            public ByteString getTranslatedBytes() {
                Object ref = this.translated_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.translated_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTranslated(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.translated_ = value;
                onChanged();
                return this;
            }

            public Builder clearTranslated() {
                this.bitField0_ &= -5;
                this.translated_ = response.getDefaultInstance().getTranslated();
                onChanged();
                return this;
            }

            public Builder setTranslatedBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.translated_ = value;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m968setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m962mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static response getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<response> parser() {
            return PARSER;
        }

        public Parser<response> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public response m924getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = {"\n\btr.proto\"D\n\bunsigned\u0012\r\n\u0005huuid\u0018\u0001 \u0001(\t\u0012\u0012\n\u0007version\u0018\u0002 \u0001(\u0004:\u00010\u0012\u0015\n\ngeneration\u0018\u0003 \u0001(\u0004:\u00010\"i\n\u0005token\u0012\u0012\n\u0007version\u0018\u0001 \u0001(\u0004:\u00011\u0012\u0010\n\u0005keyid\u0018\u0002 \u0001(\u0004:\u00011\u0012\u0015\n\ngeneration\u0018\u0003 \u0001(\u0004:\u00010\u0012\u0010\n\bunsigned\u0018\u0004 \u0001(\f\u0012\u0011\n\tsignature\u0018\u0005 \u0001(\f\"\u008f\u0001\n\u0007request\u0012\u0012\n\u0007version\u0018\u0001 \u0001(\u0004:\u00011\u0012\u0015\n\u0005token\u0018\u0002 \u0001(\u000b2\u0006.token\u0012\u0013\n\u000btotranslate\u0018\u0003 \u0001(\t\u0012\u0014\n\flanguagecode\u0018\u0004 \u0001(\t\u0012\u0016\n\u000esourcelanguage\u0018\u0005 \u0001(\t\u0012\u0016\n\u000etargetlanguage\u0018\u0006 \u0001(\t\"E\n\bresponse\u0012\u0012\n\u0007version\u0018\u0001 \u0001(\u0004:\u00011\u0012\u0011\n\u0006status\u0018\u0002 \u0001(\u0004:\u00010\u0012\u0012\n\ntranslated\u0018\u0003", " \u0001(\tBF\n3com.google.appinventor.components.runtime.translateB\u000fTranslatorToken"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.google.appinventor.components.runtime.translate.TranslatorToken.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                TranslatorToken.descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_unsigned_descriptor = descriptor2;
        internal_static_unsigned_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Huuid", "Version", "Generation"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_token_descriptor = descriptor3;
        internal_static_token_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Version", "Keyid", "Generation", "Unsigned", "Signature"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_request_descriptor = descriptor4;
        internal_static_request_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Version", "Token", "Totranslate", "Languagecode", "Sourcelanguage", "Targetlanguage"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_response_descriptor = descriptor5;
        internal_static_response_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Version", "Status", "Translated"});
    }
}
